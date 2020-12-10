package com.hsr.yxw.util;

import com.hsr.yxw.common.InitializedConfig;
import com.hsr.yxw.common.WebConstants;
import org.apache.commons.io.FileUtils;
import org.springframework.util.ResourceUtils;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.introspector.Property;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public abstract class ConfigUtils {
    private static final ConcurrentHashMap<Class<?>, AtomicLong> sequenceMapByClass = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<Class<?>, Yaml> yamlObjByClass = new ConcurrentHashMap<>();

    /**
     * 根据不同的类镜像获取Yaml对象
     */
    private static Yaml getYamlObj(final Class<?> clazz) {
        return yamlObjByClass.computeIfAbsent(clazz, key -> {
            Representer representer = new Representer() {
                @Override
                protected NodeTuple representJavaBeanProperty(Object javaBean, Property property, Object propertyValue, Tag customTag) {
                    // if value of property is null, ignore it. 忽略null字段
                    if (propertyValue == null) {
                        return null;
                    } else {
                        return super.representJavaBeanProperty(javaBean, property, propertyValue, customTag);
                    }
                }
            };
            representer.getPropertyUtils().setSkipMissingProperties(true);
            return new Yaml(new Constructor(clazz), representer);
        });
    }

    /**
     * 根据类镜像获取一个递增的序列，（每一个类镜像对应一个序列）
     *
     * @param clazz 类镜像
     */
    public static long getSequenceByClass(Class<?> clazz) {
        AtomicLong atomicLong = sequenceMapByClass.computeIfAbsent(clazz, key -> new AtomicLong(0L));
        return atomicLong.incrementAndGet();
    }

    /**
     * 根据yaml配置获取配置类对象
     *
     * @param clazz     配置类镜像
     * @param useBase64 是否需要base64加密和解密
     */
    public static <T> T getYxwConfig(Class<?> clazz, boolean useBase64) throws IOException, InstantiationException, IllegalAccessException {
        File rootDir = new File(ResourceUtils.getURL("classpath:").getPath());
        File configDir = new File(rootDir, "config");
        if (!configDir.exists()) {
            configDir.mkdirs();
        }
        File file = new File(configDir, clazz.getSimpleName());
        if (!file.exists()) {
            // 文件不存在，创建文件，初始化配置并写入
            file.createNewFile();

            return (T) initYxwConfig(file, clazz, useBase64);
        } else {
            String sourceString = FileUtils.readFileToString(file, WebConstants.DEFAULT_ENCODING);
            if (useBase64) {
                byte[] decode = Base64.getDecoder().decode(sourceString);
                sourceString = new String(decode, WebConstants.DEFAULT_ENCODING);
            }
            T yxwConfig = (T) getYamlObj(clazz).loadAs(sourceString, clazz);
            if (yxwConfig == null) {
                return (T) initYxwConfig(file, clazz, useBase64);
            } else {
                return yxwConfig;
            }
        }
    }

    /**
     * 初始化主配置
     */
    private static <T> T initYxwConfig(File file, Class clazz, boolean useBase64) throws IOException, IllegalAccessException, InstantiationException {
        Object yxwConfig = clazz.newInstance();
        if (yxwConfig instanceof InitializedConfig) {
            ((InitializedConfig) yxwConfig).init();
        }
        String configString = getYamlObj(clazz).dumpAsMap(yxwConfig);
        if (useBase64) {
            configString = Base64.getEncoder().encodeToString(configString.getBytes(WebConstants.DEFAULT_ENCODING));
        }
        FileUtils.writeStringToFile(file, configString, WebConstants.DEFAULT_ENCODING);
        return (T) yxwConfig;
    }
}
