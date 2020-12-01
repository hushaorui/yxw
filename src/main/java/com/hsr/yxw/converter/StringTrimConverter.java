package com.hsr.yxw.converter;

import org.springframework.core.convert.converter.Converter;

/**
 * 暂时不考虑使用
 */
public class StringTrimConverter implements Converter<String, String> {
    @Override
    public String convert(String s) {
        if (s == null) {
            return null;
        }
        return s.trim();
    }
}