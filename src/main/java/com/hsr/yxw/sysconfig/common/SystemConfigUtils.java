package com.hsr.yxw.sysconfig.common;

import com.hsr.yxw.common.CommonResult;
import com.hsr.yxw.sysconfig.pojo.SystemConfig;

public class SystemConfigUtils {
    
    public static CommonResult checkSystemConfig(SystemConfig systemConfig) {
        if (systemConfig.getClassify() == null || "".equals(systemConfig.getClassify().trim())) {
            return CommonResult.error("类别不可为空！");
        }
        String configValue = systemConfig.getConfigValue();
        if (configValue == null || "".equals(configValue)) {
            return CommonResult.error("值不可为空！");
        }
        if (systemConfig.getValueType() == null || "".equals(systemConfig.getValueType().trim())) {
            return CommonResult.error("值类型不可为空！");
        }
        if (String.class.getSimpleName().equals(systemConfig.getValueType())) {
            // ignore 忽略
        } else if (Byte.class.getSimpleName().equals(systemConfig.getValueType())) {
            try {
                Byte.parseByte(systemConfig.getConfigValue());
            } catch (NumberFormatException e) {
                return CommonResult.error("值：" + systemConfig.getConfigValue() + "不符合类型：" + systemConfig.getValueType());
            }
        } else if (Short.class.getSimpleName().equals(systemConfig.getValueType())) {
            try {
                Short.parseShort(systemConfig.getConfigValue());
            } catch (NumberFormatException e) {
                return CommonResult.error("值：" + systemConfig.getConfigValue() + "不符合类型：" + systemConfig.getValueType());
            }
        } else if (Integer.class.getSimpleName().equals(systemConfig.getValueType())) {
            try {
                Integer.parseInt(systemConfig.getConfigValue());
            } catch (NumberFormatException e) {
                return CommonResult.error("值：" + systemConfig.getConfigValue() + "不符合类型：" + systemConfig.getValueType());
            }
        } else if (Long.class.getSimpleName().equals(systemConfig.getValueType())) {
            try {
                Long.parseLong(systemConfig.getConfigValue());
            } catch (NumberFormatException e) {
                return CommonResult.error("值：" + systemConfig.getConfigValue() + "不符合类型：" + systemConfig.getValueType());
            }
        } else if (Boolean.class.getSimpleName().equals(systemConfig.getValueType())) {
            if  (! configValue.toLowerCase().matches("true|false")) {
                return CommonResult.error("值：" + systemConfig.getConfigValue() + "不符合类型：" + systemConfig.getValueType());
            }
        } else if (Float.class.getSimpleName().equals(systemConfig.getValueType())) {
            try {
                Float.parseFloat(systemConfig.getConfigValue());
            } catch (NumberFormatException e) {
                return CommonResult.error("值：" + systemConfig.getConfigValue() + "不符合类型：" + systemConfig.getValueType());
            }
        } else if (Double.class.getSimpleName().equals(systemConfig.getValueType())) {
            try {
                Double.parseDouble(systemConfig.getConfigValue());
            } catch (NumberFormatException e) {
                return CommonResult.error("值：" + systemConfig.getConfigValue() + "不符合类型：" + systemConfig.getValueType());
            }
        } else {
            return CommonResult.error("未知的值类型：" + systemConfig.getValueType());
        }
        return null;
    }
}
