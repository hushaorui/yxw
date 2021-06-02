package com.hsr.game.common;

/**
 * 双值
 * @param <T1> 值1
 * @param <T2> 值2
 */
public class TwinsValue<T1, T2> {
    private T1 value1;
    private T2 value2;

    public TwinsValue(T1 value1, T2 value2) {
        this.value1 =value1;
        this.value2 = value2;
    }
    public TwinsValue() {}

    public T1 getValue1() {
        return value1;
    }

    public void setValue1(T1 value1) {
        this.value1 = value1;
    }

    public T2 getValue2() {
        return value2;
    }

    public void setValue2(T2 value2) {
        this.value2 = value2;
    }
}
