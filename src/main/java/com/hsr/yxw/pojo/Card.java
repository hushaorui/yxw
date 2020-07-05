package com.hsr.yxw.pojo;

import com.hsr.yxw.em.CardType;

import java.io.Serializable;

public class Card implements Serializable {
    private static final long serialVersionUID = 0L;
    private Long id;
    private String name;
    /** 卡片类型 */
    private CardType cardType;
    /** 描述 */
    private String description;

    private String imageUrl;

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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
