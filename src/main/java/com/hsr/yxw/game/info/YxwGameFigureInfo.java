package com.hsr.yxw.game.info;

import com.hsr.yxw.game.common.em.skill.YxwGameSkill;

import java.io.Serializable;
import java.util.List;

/**
 * 游戏里的人物
 */
public class YxwGameFigureInfo implements Serializable {
    private Long id;
    /** 人物名称，如 武藤游戏 */
    private String name;
    /** 人物描述 */
    private String description;
    /** 人物样貌图片路径 */
    private String appearanceImgUrl;
    /** 人物技能id */
    private List<YxwGameSkill> gameSkills;

    public YxwGameFigureInfo() {}

    public YxwGameFigureInfo(Long id, String name, String description, String appearanceImgUrl, List<YxwGameSkill> gameSkills) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.appearanceImgUrl = appearanceImgUrl;
        this.gameSkills = gameSkills;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAppearanceImgUrl() {
        return appearanceImgUrl;
    }

    public void setAppearanceImgUrl(String appearanceImgUrl) {
        this.appearanceImgUrl = appearanceImgUrl;
    }

    public List<YxwGameSkill> getGameSkills() {
        return gameSkills;
    }

    public void setGameSkills(List<YxwGameSkill> gameSkills) {
        this.gameSkills = gameSkills;
    }
}
