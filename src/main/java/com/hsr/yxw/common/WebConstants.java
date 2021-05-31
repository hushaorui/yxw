package com.hsr.yxw.common;

public interface WebConstants {

    String SYSTEM = "system";

    String WARNING = "warning";
    String INFO = "info";
    String ERROR = "error";
    String SUCCESS = "success";

    String NOT_FOUND = "not found";

    String DEFAULT_ENCODING = "UTF-8";

    /** 中文、大小写字母、数字 正则表达式 */
    String CHINESE_ENGLISH_NUMBER_REGEX = "[0-9a-zA-Z\u4e00-\u9fa5]+";

    // 以逗号切割，保留双引号内逗号且去除两端空格的正则表达式
    String COMMA_SPLIT_REGEX = "\\s*,(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)\\s*";
    // 以分号切割，保留双引号内分号且去除两端空格的正则表达式
    String SEMICOLON_SPLIT_REGEX = "\\s*;(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)\\s*";
    // 以等号切割，保留双引号内等号且去除两端空格的正则表达式
    String EQUAL_SIGN_SPLIT_REGEX = "\\s*=(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)\\s*";
    // 以冒号切割，保留双引号内冒号且去除两端空格的正则表达式
    String COLON_SPLIT_REGEX = "\\s*:(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)\\s*";
    // 以空白字符切割，保留双引号内空白字符的正则表达式
    String BLANK_SPLIT_REGEX = "\\s+(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)";
}
