package com.hsr.yxw.converter;

import com.alibaba.druid.util.StringUtils;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.util.Date;

public class StringToLongConverter implements Converter<String, Long> {
    @Override
    public Long convert(String source) {
        if (source == null || StringUtils.isEmpty(source.trim())) {
            return null;
        }
        source = source.trim();
        if (source.matches("\\d+")) {
            return Long.parseLong(source);
        }
        // 尝试转换时间格式的字符串为时间戳
        Date date = null;
        try {
            date = StringToTimestampConverter.dateFormat.parse(source);
        } catch (ParseException e) {
            // 解析失败，使用另一种格式进行解析
            try {
                date = StringToTimestampConverter.dateFormat2.parse(source);
            } catch (ParseException ignore) {
                // 仍然解析失败
            }
        }
        if (date != null) {
            return date.getTime();
        }
        return null;
    }
}