package com.hsr.yxw.ws.chat.service;

import com.alibaba.fastjson.JSONArray;
import com.hsr.yxw.common.CommandLine;
import com.hsr.yxw.common.NoParentCustomClassLoader;
import com.hsr.yxw.common.WebConstants;
import com.hsr.yxw.util.MD5Utils;
import com.hsr.yxw.util.PackageUtil;
import com.hsr.yxw.util.SpringContextUtil;
import com.hsr.yxw.ws.chat.common.AliasName;
import com.hsr.yxw.ws.chat.common.GmExeResult;
import com.hsr.yxw.ws.chat.common.GmExecutor;
import com.hsr.yxw.ws.chat.common.GmMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class GmMainService {
    private static final Log log = LogFactory.getLog(GmMainService.class);
    private Map<String, GmMethod> methodMap;
    private Map<String, Object> lastVersionMapping = new ConcurrentHashMap<>();
    private String executorPackage = "com.hsr.yxw.ws.chat.gm";

    public List<GmExeResult> executeCmd(String message) {
        List<GmExeResult> gmExeResultList;
        try {
            if (methodMap == null) {
                initMethodMap();
            }
            // 切割字符串，每一个字符串都是一个命令
            String[] cmdArray = message.split(WebConstants.SEMICOLON_SPLIT_REGEX);
            gmExeResultList = new ArrayList<>(cmdArray.length);
            // 暂存数据集合，所有命令都会先校验格式是否正确，才会执行，只要有一个命令不符合条件，则全部不执行
            Map<String, String[]> tempDataMap = new HashMap<>();
            String resultMessage = null;
            for (String cmdString : cmdArray) {
                if ("".equals(cmdString)) {
                    continue;
                }
                String[] cmdItemArray = cmdString.split(WebConstants.BLANK_SPLIT_REGEX);
                if (cmdItemArray.length < 2) {
                    resultMessage = String.format("该命令太短：%s", cmdString);
                    break;
                }
                if (! cmdItemArray[0].equals("*")) {
                    resultMessage = String.format("命令必须以*开头：%s", cmdString);
                    break;
                }
                // 命令方法名称
                String methodName = cmdItemArray[1];
                GmMethod gmMethod = methodMap.get(methodName);
                if (gmMethod == null) {
                    resultMessage = String.format("命令不存在：%s", methodName);
                    break;
                }
                tempDataMap.put(cmdString, cmdItemArray);
            }
            if (resultMessage != null) {
                // 校验不通过
                log.error(resultMessage);
                gmExeResultList.add(GmExeResult.failure(resultMessage));
                return gmExeResultList;
            }
            // 执行这些命令
            for (Map.Entry<String, String[]> entry : tempDataMap.entrySet()) {
                String[] cmdItemArray = entry.getValue();
                String methodName = cmdItemArray[1];
                GmMethod gmMethod = methodMap.get(methodName);
                Method method = gmMethod.getMethod();
                CommandLine commandLine = CommandLine.parseStringArray(cmdItemArray, 2);
                // 获得方法参数列表的类型
                Class<?>[] parameterTypes = method.getParameterTypes();
                Object[] executeParameterArray = new Object[parameterTypes.length];
                for (int index = 0; index < parameterTypes.length; index ++) {
                    Class<?> parameterType = parameterTypes[index];
                    if (parameterType.isAssignableFrom(CommandLine.class)) {
                        executeParameterArray[index] = commandLine;
                    } else {
                        Object bean = SpringContextUtil.getBean(parameterType);
                        if (bean != null) {
                            executeParameterArray[index] = bean;
                        }
                    }
                }
                Object invokeReturnObj = method.invoke(gmMethod.getExecutor(), executeParameterArray);
                if (invokeReturnObj != null && GmExeResult.class.isAssignableFrom(invokeReturnObj.getClass())) {
                    gmExeResultList.add((GmExeResult) invokeReturnObj);
                }
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return Collections.emptyList();
        }
        return gmExeResultList;
    }
    private void hotfixGm(String filePath) {
        log.info("Gm热更开始======");
        Map<String, GmMethod> methodMap = new HashMap<>();
        // 获取当前执行目录
        // String classPath = Objects.requireNonNull(GmMainService.class.getClassLoader().getResource("")).getPath();
        File executorDir = new File(filePath);
        if (! executorDir.exists()) {
            throw new RuntimeException("Gm反射执行类目录不存在：" + executorDir.getAbsolutePath());
        }
        // TODO

        this.methodMap = methodMap;
        log.info("Gm热更结束======");
    }

    private void initMethodMap() {
        log.info("Gm初始化开始======");
        Map<String, GmExecutor> executorMap = new HashMap<>();
        Map<String, GmMethod> methodMap = new HashMap<>();
        // 获取所有该目录下的类名
        List<String> classNameList = PackageUtil.getClassName(executorPackage);
        log.info("获取所有GmExecutor类名：" + JSONArray.toJSONString(classNameList));
        ClassLoader classLoader = GmMainService.class.getClassLoader();
        for (String className : classNameList) {
            try {
                Class<?> clazz = classLoader.loadClass(className);
                // 判断该类是否是Executor
                Class<?> superClass = clazz.getSuperclass();
                if (superClass != null && GmExecutor.class.getName().equals(superClass.getName())) {
                    Object executor = clazz.newInstance();
                    // 设置默认的md5值
                    String md5Value = "default";
                    setMd5Value(executor, md5Value);
                    executorMap.put(className, (GmExecutor) executor);
                }
            } catch (Exception e) {
                log.error("Gm初始化出现异常", e);
            }
        }
        log.info("创建所有GmExecutor实例完毕");
        HashSet<String> cmdNames = new HashSet<>();
        // 遍历，进行初始化
        for (GmExecutor executor : executorMap.values()) {
            //executor.initExecutor();
            Method[] declaredMethods = executor.getClass().getDeclaredMethods();
            for (Method method : declaredMethods) {
                Class<?> returnType = method.getReturnType();
                if (! GmExeResult.class.isAssignableFrom(returnType)) {
                    // 方法返回值不为 GmExeResult 或其字类的，跳过
                    continue;
                }
                int modifiers = method.getModifiers();
                if (Modifier.isStatic(modifiers)) {
                    // 静态的方法，跳过
                    continue;
                }
                // 映射的命令的名称，注意，这里必为小写
                String mappingName;
                AliasName aliasName = method.getDeclaredAnnotation(AliasName.class);
                if (aliasName != null) {
                    mappingName = aliasName.name().toLowerCase();
                } else {
                    mappingName = method.getName().toLowerCase();
                }
                if (methodMap.containsKey(mappingName)) {
                    GmMethod gmMethod = methodMap.get(mappingName);
                    log.error(String.format("方法被覆盖，原类名：%s，原方法名：%s，覆盖的类名：%s，覆盖的方法名：%s",
                            gmMethod.getExecutor().getClass().getName(), gmMethod.getMethod().getName(), executor.getClass().getName(), method.getName()));
                }
                methodMap.put(mappingName, new GmMethod(executor, method));
            }
            // 加入到版本控制集合中
            addToVersionClassNameMapping(executor);
        }
        // 打印一下
        System.out.println(versionClassNameMapping);
        System.out.println(getLastVersionMapping());
        this.methodMap = methodMap;
        log.info("Gm初始化结束======");
    }

    // <全类名, <版本, GmExecutor 子类>>
    private Map<String, Map<Integer, Object>> versionClassNameMapping = new ConcurrentHashMap<>();

    public Map<String, Object> getLastVersionMapping() {
        return lastVersionMapping;
    }

    private boolean addToVersionClassNameMapping(Object executor) {
        if (executor == null) {
            return false;
        }
        String md5Value = getMd5Value(executor);
        if (md5Value == null) {
            System.out.println("Executor 未完成指定的初始化，无法使用！class: " + executor.getClass().getName());
            return false;
        }
        boolean hasSameMd5WithLastVersionExecutor = false;
        String className = executor.getClass().getName();
        if (versionClassNameMapping.containsKey(className) && lastVersionMapping.containsKey(className)) {
            Object lastVersionExecutor = lastVersionMapping.get(className);
            hasSameMd5WithLastVersionExecutor = md5Value.equals(getMd5Value(lastVersionExecutor));
            if (! hasSameMd5WithLastVersionExecutor) {
                // 只有md5和上一次的不一样时才会进行更迭
                Map<Integer, Object> map = versionClassNameMapping.get(className);
                int version = getVersion(lastVersionExecutor) + 1;
                setExecutorValue(executor, "version", version);
                map.put(version, executor);
            }
        } else {
            ConcurrentHashMap<Integer, Object> concurrentHashMap = new ConcurrentHashMap<>();
            setExecutorValue(executor, "version", 1);
            concurrentHashMap.put(1, executor);
            versionClassNameMapping.put(className, concurrentHashMap);
        }
        if (! hasSameMd5WithLastVersionExecutor) {
            // 最新版本，直接覆盖
            lastVersionMapping.put(className, executor);
            return true;
        } else {
            return false;
        }
    }

    public String getMd5Value(Object executor) {
        return getExecutorValue(executor, "md5Value");
    }
    public void setMd5Value(Object executor, String md5Value) {
        setExecutorValue(executor, "md5Value", md5Value);
    }
    public Long getCreateTime(Object executor) {
        return getExecutorValue(executor, "createTime");
    }
    public Integer getVersion(Object executor) {
        return getExecutorValue(executor, "version");
    }

    private <T> T getExecutorValue(Object executor, String filedName) {
        try {
            if (executor == null) {
                return null;
            } else {
                Class<?> gmExecutorClass = executor.getClass().getSuperclass();
                if (gmExecutorClass == null) {
                    throw new RuntimeException("getExecutorValue: " + executor.getClass() + "is not a GmExecutor.");
                }
                Field field = gmExecutorClass.getDeclaredField(filedName);
                field.setAccessible(true);
                return (T) field.get(executor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private void setExecutorValue(Object executor, String fieldName, Object fieldValue) {
        try {
            if (executor == null) {
                return;
            }
            Class<?> gmExecutorClass = executor.getClass().getSuperclass();
            if (gmExecutorClass == null) {
                throw new RuntimeException("setExecutorValue: " + executor.getClass() + "is not a GmExecutor.");
            }
            Field field = gmExecutorClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(executor, fieldValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void initExecutor(Object executor) {
        try {
            Method method = executor.getClass().getDeclaredMethod("initExecutor", null);
            method.invoke(executor, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
