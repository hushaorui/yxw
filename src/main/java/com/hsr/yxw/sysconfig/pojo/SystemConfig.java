package com.hsr.yxw.sysconfig.pojo;

public class SystemConfig {

    private Long id;
    /**唯一，不为空*/
    private String configKey;
    /** 分类 */
    private String classify;
    /** 实际值 */
    private String configValue;
    /** 实际值的类型 */
    private String valueType;

    public SystemConfig() {}

    public SystemConfig(String configKey, String classify, String configValue, Class<?> valueType) {
        this.configKey = configKey;
        this.classify = classify;
        this.configValue = configValue;
        this.valueType = valueType.getSimpleName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }
}
