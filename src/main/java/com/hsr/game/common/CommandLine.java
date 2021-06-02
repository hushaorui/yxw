package com.hsr.game.common;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * 一行命令对象
 */
public class CommandLine {
    private static final CommandLine DEFAULT_OBJ = new CommandLine(Collections.emptyMap());
    // 所有参数集合
    private Map<String, String> optionMap;
    private CommandLine() {}

    public CommandLine(Map<String, String> optionMap) {
        this.optionMap = optionMap;
    }
    public static CommandLine parseStringArray(String[] stringArray) {
        return parseStringArray(stringArray, 0);
    }
    public static CommandLine parseStringArray(String[] stringArray, int ignoreStartLength) {
        if (stringArray == null || stringArray.length - ignoreStartLength <= 0) {
            return DEFAULT_OBJ;
        }
        CommandLine commandLine = new CommandLine();
        Map<String, String> hashMap = new HashMap<>();
        String currentOption = null;
        int ignoreLength = 0;
        for (String commandString : stringArray) {
            if (ignoreLength < ignoreStartLength) {
                ignoreLength ++;
                continue;
            }
            if (commandString.startsWith("-")) {
                currentOption = commandString.substring(1);
                // 先将自身放入map，防止后面没有参数而遗失数据
                hashMap.put(currentOption, currentOption);
                continue;
            }
            if (commandString.length() >= 2) {
                if (commandString.startsWith("\"") && commandString.endsWith("\"")) {
                    // 去除两端双引号
                    commandString = commandString.substring(1, commandString.length() - 1);
                } else if (commandString.startsWith("'") && commandString.endsWith("'")) {
                    // 去除两端单引号
                    commandString = commandString.substring(1, commandString.length() - 1);
                }
            }
            if (currentOption == null) {
                // 没有参数项，只能将自己作为key
                hashMap.put(commandString, commandString);
            } else {
                hashMap.put(currentOption, commandString);
                currentOption = null;
            }
        }
        commandLine.optionMap = hashMap;
        return commandLine;
    }

    /**
     * 获取原始map中的字符串数据
     */
    public String getOption(String key) {
        return optionMap.get(key);
    }

    public String getOption(String key, String defaultValue) {
        if (optionMap.containsKey(key)) {
            return optionMap.get(key);
        }
        return defaultValue;
    }

    public byte getByteValue(String key, byte defaultValue) {
        String string = optionMap.get(key);
        if (string == null) {
            return defaultValue;
        }
        try {
            return Byte.parseByte(string);
        } catch (Exception ignore) {
            return defaultValue;
        }
    }
    public short getShortValue(String key, short defaultValue) {
        String string = optionMap.get(key);
        if (string == null) {
            return defaultValue;
        }
        try {
            return Short.parseShort(string);
        } catch (Exception ignore) {
            return defaultValue;
        }
    }
    public int getIntValue(String key, int defaultValue) {
        String string = optionMap.get(key);
        if (string == null) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(string);
        } catch (Exception ignore) {
            return defaultValue;
        }
    }
    public long getLongValue(String key, long defaultValue) {
        String string = optionMap.get(key);
        if (string == null) {
            return defaultValue;
        }
        try {
            return Long.parseLong(string);
        } catch (Exception ignore) {
            return defaultValue;
        }
    }
    public float getFloatValue(String key, float defaultValue) {
        String string = optionMap.get(key);
        if (string == null) {
            return defaultValue;
        }
        try {
            return Float.parseFloat(string);
        } catch (Exception ignore) {
            return defaultValue;
        }
    }
    public double getDoubleValue(String key, double defaultValue) {
        String string = optionMap.get(key);
        if (string == null) {
            return defaultValue;
        }
        try {
            return Double.parseDouble(string);
        } catch (Exception ignore) {
            return defaultValue;
        }
    }
    public boolean getBoolean(String key) {
        String string = optionMap.get(key);
        if (string == null) {
            return false;
        }
        if (string.equals(key)) {
            return true;
        }
        try {
            return Boolean.parseBoolean(string);
        } catch (Exception ignore) {
            return false;
        }
    }
    public List<String> getStringList(String key, String split) {
        String string = optionMap.get(key);
        if (string == null) {
            return Collections.emptyList();
        }
        String[] array = string.split(split);
        return new ArrayList<>(Arrays.asList(array));
    }
    public List<Integer> getIntegerList(String key, String split) {
        String string = optionMap.get(key);
        if (string == null) {
            return Collections.emptyList();
        }
        String[] array = string.split(split);
        List<Integer> list = new ArrayList<>(array.length);
        for (String numberString : array) {
            list.add(Integer.parseInt(numberString));
        }
        return list;
    }
    public Map<Integer, Integer> getIntIntMap(String key, String split1, String split2) {
        String string;
        if (optionMap.containsKey(key)) {
            string = optionMap.get(key);
        } else {
            return Collections.emptyMap();
        }
        String[] array = string.split(split1);
        Map<Integer, Integer> hashMap = new HashMap<>(array.length / 3 * 4 + 1);
        for (String tempEntry : array) {
            String[] temp = tempEntry.split(split2);
            int mapKey = Integer.parseInt(temp[0]);
            int mapValue;
            if (temp.length >= 2) {
                mapValue = Integer.parseInt(temp[1]);
            } else {
                // 默认为1
                mapValue = 1;
            }
            hashMap.put(mapKey, mapValue);
        }
        return hashMap;
    }

    public Map<String, Integer> getStringIntMap(String key, String split1, String split2) {
        String string = optionMap.get(key);
        if (string == null) {
            return Collections.emptyMap();
        }
        String[] array = string.split(split1);
        Map<String, Integer> hashMap = new HashMap<>(array.length / 3 * 4 + 1);
        for (String tempEntry : array) {
            String[] temp = tempEntry.split(split2);
            int mapValue;
            if (temp.length >= 2) {
                mapValue = Integer.parseInt(temp[1]);
            } else {
                // 默认为1
                mapValue = 1;
            }
            hashMap.put(temp[0], mapValue);
        }
        return hashMap;
    }

    public File getFile(String key, boolean createIfNotExist) {
        String string = optionMap.get(key);
        if (string == null) {
            return null;
        }
        File file = new File(string);
        if (! file.exists()) {
            try {
                if (createIfNotExist) {
                    file.createNewFile();
                    return file;
                } else {
                    return null;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return file;
        }
    }
}
