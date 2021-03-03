package com.hsr.yxw.game.data;

import com.hsr.yxw.common.TwinsValue;
import com.hsr.yxw.ws.game.base.pojo.YxwGameMainData;
import com.hsr.yxw.ws.game.figure.pojo.YxwGameFigureData;

import java.util.*;

public enum YxwGameDataType {

    /** 人物数据 */
    Figure(YxwGameFigureData.class),
    /** 拥有的卡牌id数据 */
    Card(Long.class),
    /** 拥有的物品以及对应数量数据 */
    Goods(TwinsValue.class, Long.class, Long.class),
    /** 基础数据 */
    Main(YxwGameMainData.class),
    ;
    private static final Map<Class<?>, YxwGameDataType> singleClassMapping;
    static {
        YxwGameDataType[] values = YxwGameDataType.values();
        Map<Class<?>, YxwGameDataType> temp = new HashMap<>();
        // 超过两种相同类型的数据，不适合放入此集合
        Set<YxwGameDataType> moreThanOneSet = new HashSet<>();
        for (YxwGameDataType dataType : values) {
            Class<?> aClass = dataType.getDataClass();
            if (temp.containsKey(aClass)) {
                temp.remove(aClass);
                moreThanOneSet.add(dataType);
                continue;
            }
            if (moreThanOneSet.contains(dataType)) {
                continue;
            }
            temp.put(aClass, dataType);
        }
        singleClassMapping = Collections.unmodifiableMap(temp);
    }
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

    public static YxwGameDataType getBySingleDataClass(Class<?> aClass) {
        return singleClassMapping.get(aClass);
    }

    public Class<?> getDataClass() {
        return dataClass;
    }

    public List<Class<?>> getOtherClassList() {
        return otherClassList;
    }
}
