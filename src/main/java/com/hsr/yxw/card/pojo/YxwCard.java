package com.hsr.yxw.card.pojo;

import com.hsr.yxw.card.common.YxwCardType;

/**
 * 游戏王卡牌，基本信息
 */
public class YxwCard {
    private Long id;
    private String cardName; //名称
    private String localImgUrl; //本地图片地址
    private String remoteImgUrl; //网络图片地址
    private String description; // 描述
    private YxwCardType cardType; // 卡片类型

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
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

    public YxwCardType getCardType() {
        return cardType;
    }

    public void setCardType(YxwCardType cardType) {
        this.cardType = cardType;
    }
}
