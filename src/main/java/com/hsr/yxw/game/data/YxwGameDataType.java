package com.hsr.yxw.game.data;

public enum YxwGameDataType {

    /** 人物数据 */
    Figure(Long.class),
    /** 拥有的卡牌id数据 */
    Card(Long.class),

    ;
    /** 数据类型对应的类镜像 */
    private final Class<?> dataClass;
    YxwGameDataType(Class<?> dataClass) {
        this.dataClass = dataClass;
    }
    public Class<?> getDataClass() {
        return dataClass;
    }
}
