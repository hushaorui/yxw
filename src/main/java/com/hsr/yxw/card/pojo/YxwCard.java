package com.hsr.yxw.card.pojo;

/**
 * 游戏王卡牌，基本信息
 */
public class YxwCard {
    private Long id;
    private String name; //名称
    private String localImgUrl; //本地图片地址
    private String remoteImgUrl; //网络图片地址
    private String description; // 描述

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocalImgUrl() {
        return localImgUrl;
    }

    public void setLocalImgUrl(String localImgUrl) {
        this.localImgUrl = localImgUrl;
    }

    public String getRemoteImgUrl() {
        return remoteImgUrl;
    }

    public void setRemoteImgUrl(String remoteImgUrl) {
        this.remoteImgUrl = remoteImgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}