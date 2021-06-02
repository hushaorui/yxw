package com.hsr.game.yxw.info;

import com.hsr.game.common.LongIdInfoIF;
import com.hsr.game.yxw.common.em.card.YxwCardType;
import com.hsr.game.yxw.common.em.card.YxwDLRareLevel;

/**
 * 游戏王卡牌，基本信息
 */
public class YxwCardBaseInfo implements LongIdInfoIF {
    private Long id;
    private String cardName; //名称
    private String localImgUrl; //本地图片地址
    private String remoteImgUrl; //网络图片地址
    private String description; // 描述
    private YxwCardType cardType; // 卡片类型
    /** 在决斗链接国际服的稀有度 */
    private YxwDLRareLevel dlRareLevel;
    /** 在决斗链接国服的稀有度 */
    private YxwDLRareLevel dlCNRareLevel;

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

    public YxwDLRareLevel getDlCNRareLevel() {
        return dlCNRareLevel;
    }

    public void setDlCNRareLevel(YxwDLRareLevel dlCNRareLevel) {
        this.dlCNRareLevel = dlCNRareLevel;
    }
}
