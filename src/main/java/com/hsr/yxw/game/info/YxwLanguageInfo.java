package com.hsr.yxw.game.info;

public class YxwLanguageInfo {
    private Long id;
    //zh_CN
    //zh_TW
    //en
    private String zh_CN; // 简体中文
    private String zh_TW; // 繁体中文
    private String en; // 英文

    public YxwLanguageInfo() {}

    /** 只有中文 */
    public YxwLanguageInfo(Long id, String zh_CN) {
        this.id = id;
        this.zh_CN = zh_CN;
    }

    public YxwLanguageInfo(Long id, String zh_CN, String zh_TW, String en) {
        this.id = id;
        this.zh_CN = zh_CN;
        this.zh_TW = zh_TW;
        this.en = en;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getZh_CN() {
        return zh_CN;
    }

    public void setZh_CN(String zh_CN) {
        this.zh_CN = zh_CN;
    }

    public String getZh_TW() {
        return zh_TW;
    }

    public void setZh_TW(String zh_TW) {
        this.zh_TW = zh_TW;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }
}
