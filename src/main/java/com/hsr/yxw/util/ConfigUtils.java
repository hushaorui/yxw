package com.hsr.yxw.util;

import com.hsr.yxw.common.WebConstants;
import com.hsr.yxw.common.YxwConfig;
import org.apache.commons.io.FileUtils;
import org.springframework.util.ResourceUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

public abstract class ConfigUtils {
    public static <T> T getYxwConfig(Class<?> clazz, boolean useBase64) throws IOException, InstantiationException, IllegalAccessException {
        File rootDir = new File(ResourceUtils.getURL("classpath:").getPath());
        File configDir = new File(rootDir, "config");
        if (! configDir.exists()) {
            configDir.mkdirs();
        }
        File file = new File(configDir, clazz.getSimpleName());
        if (! file.exists()) {
            // 文件不存在，创建文件，初始化配置并写入
            file.createNewFile();

            return (T) initYxwConfig(file, clazz, useBase64);
        } else {
            String sourceString = FileUtils.readFileToString(file, WebConstants.DEFAULT_ENCODING);
            if (useBase64) {
                byte[] decode = Base64.getDecoder().decode(sourceString);
                sourceString = new String(decode, WebConstants.DEFAULT_ENCODING);
            }
            T yxwConfig = (T) new Yaml().loadAs(sourceString, clazz);
            if (yxwConfig == null) {
                return (T) initYxwConfig(file, clazz, useBase64);
            } else {
                return yxwConfig;
            }
        }
    }

    /**
     * 初始化主配置
     * @param file
     * @return
     * @throws IOException
     */
    public static <T> T initYxwConfig(File file, Class clazz, boolean useBase64) throws IOException, IllegalAccessException, InstantiationException {
        Object yxwConfig = clazz.newInstance();
        if (yxwConfig instanceof YxwConfig) {
            ((YxwConfig) yxwConfig).init();
        }
        String configString = new Yaml().dumpAsMap(yxwConfig);
        if (useBase64) {
            configString = Base64.getEncoder().encodeToString(configString.getBytes(WebConstants.DEFAULT_ENCODING));
        }
        FileUtils.writeStringToFile(file, configString, WebConstants.DEFAULT_ENCODING);
        return (T) yxwConfig;
    }
}