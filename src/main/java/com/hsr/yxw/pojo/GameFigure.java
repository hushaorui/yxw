package com.hsr.yxw.pojo;

import com.hsr.yxw.em.GameSkill;

import java.io.Serializable;
import java.util.List;

/**
 * 游戏里的人物
 */
public class GameFigure implements Serializable {
    private static final long serialVersionUID = 0L;
    /** 人物名称，如 武藤游戏 */
    private String name;
    /** 人物描述 */
    private String description;
    /** 人物样貌图片路径 */
    private String appearanceImgUrl;
    /** 人物技能 */
    private List<GameSkill> gameSkills;

}
