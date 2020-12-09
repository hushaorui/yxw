package com.hsr.yxw.card.pojo;

/**
 * 游戏王卡牌，基本信息
 */
public class YxwCard {
    private Long id;
    private String cardName; //名称
    private String localImgUrl; //本地图片地址
    private String remoteImgUrl; //网络图片地址
    private String description; // 描述
    private Integer cardType; // 卡片类型

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
