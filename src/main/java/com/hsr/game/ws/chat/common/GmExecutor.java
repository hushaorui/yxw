package com.hsr.game.ws.chat.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.lang.reflect.Method;
import java.util.HashMap;

public abstract class GmExecutor {

    // class类文件的md5值
    protected String md5Value;
    // 对象创建的时间
    private long createTime;
    // 版本，从1开始
    private Integer version;

    public GmExecutor() {
        this.createTime = System.currentTimeMillis();
    }

    public final String getMd5Value() {
        return md5Value;
    }
    public final long getCreateTime() {
        return createTime;
    }
    public final Integer getVersion() {
        return version;
    }

    @Override
    public String toString() {
        return "GMExecutor{" +
                "md5Value='" + md5Value + '\'' +
                ", createTime=" + createTime +
                ", version=" + version +
                '}';
    }

    /**
     * 打印信息
     */
    protected void printMessage(Object object) {
        printMessage(object, true);
    }

    /**
     * 打印信息
     */
    protected void printMessage(Object object, boolean format) {
        String message;
        if (format) {
            message = getJsonFormatString(object);
        } else {
            message = String.valueOf(object);
        }
        System.out.println(message);
    }

    /**
     * 获得格式化可阅读的json字符串
     */
    protected String getJsonFormatString(Object object) {
        return JSON.toJSONString(object, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteDateUseDateFormat);
    }
}

