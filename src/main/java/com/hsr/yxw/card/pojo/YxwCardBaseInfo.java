package com.hsr.yxw.card.pojo;

import com.hsr.yxw.card.common.YxwCardType;
import com.hsr.yxw.card.common.em.card.YxwDLRareLevel;

/**
 * 游戏王卡牌，基本信息
 */
public class YxwCardBaseInfo {
    private Long id;
    private String cardName; //名称
    private String localImgUrl; //本地图片地址
    private String remoteImgUrl; //网络图片地址
    private String description; // 描述
    private YxwCardType cardType; // 卡片类型
    /** 在决斗链接的稀有度 */
    private YxwDLRareLevel dlRareLevel;

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

    public YxwDLRareLevel getDlRareLevel() {
        return dlRareLevel;
    }

    public void setDlRareLevel(YxwDLRareLevel dlRareLevel) {
        this.dlRareLevel = dlRareLevel;
    }
}
