package com.hsr.yxw.game.data;

import com.hsr.yxw.common.TwinsValue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum YxwGameDataType {

    /** 人物数据 */
    Figure(Long.class),
    /** 拥有的卡牌id数据 */
    Card(Long.class),
    /** 拥有的物品以及对应数量数据 */
    Goods(TwinsValue.class, Long.class, Long.class),
    ;
    /** 数据类型对应的类镜像 */
    private final Class<?> dataClass;
    /** 除主类外，其他的类型，主要是针对主类型的补充 */
    private final List<Class<?>> otherClassList;
    YxwGameDataType(Class<?>... dataClasses) {
        this.dataClass = dataClasses[0];
        if (dataClasses.length > 1) {
            List<Class<?>> otherClassList = new ArrayList<>(dataClasses.length);
            for (int i = 1; i < dataClasses.length; i++) {
                otherClassList.add(dataClasses[i]);
            }
            this.otherClassList = Collections.unmodifiableList(otherClassList);
        } else {
            otherClassList = Collections.emptyList();
        }
    }
    public Class<?> getDataClass() {
        return dataClass;
    }

    public List<Class<?>> getOtherClassList() {
        return otherClassList;
    }
}
