package com.hsr.yxw.figure.pojo;

import com.hsr.yxw.card.common.em.skill.YxwGameSkill;

import java.io.Serializable;
import java.util.List;

/**
 * 游戏里的人物
 */
public class YxwGameFigure implements Serializable {
    private static final long serialVersionUID = 0L;
    /** 人物名称，如 武藤游戏 */
    private String name;
    /** 人物描述 */
    private String description;
    /** 人物样貌图片路径 */
    private String appearanceImgUrl;
    /** 人物技能 */
    private List<YxwGameSkill> gameSkills;

}
