package com.hsr.yxw.util;

import java.util.ArrayList;

/**
 * 工具类
 */
public abstract class YxwStringUtils {

    public static ArrayList<Long> idStringToList(String idString, String separator) {
        String[] idArray = idString.split("\\s*" + separator + "\\s*");
        ArrayList<Long> idList = new ArrayList<>(idArray.length);
        for (String id : idArray) {
            try {
                idList.add(Long.parseLong(id));
            } catch (NumberFormatException ignore) {
            }
        }
        return idList;
    }
    public static ArrayList<Long> idStringToList(String idString) {
        return idStringToList(idString, ",");
    }
}
