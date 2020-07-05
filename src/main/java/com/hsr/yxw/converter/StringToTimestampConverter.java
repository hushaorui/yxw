package com.hsr.yxw.converter;

import org.springframework.core.convert.converter.Converter;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class StringToTimestampConverter implements Converter<String, Timestamp> {
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Timestamp convert(String source) {
        try {
            if (source != null) {
                return new Timestamp(dateFormat.parse(source).getTime());
            }
            throw new Exception();
        } catch (Exception e) {
            try {
                return new Timestamp(dateFormat2.parse(source).getTime());
            } catch (ParseException e1) {
                return null;
            }
        }
    }
}
