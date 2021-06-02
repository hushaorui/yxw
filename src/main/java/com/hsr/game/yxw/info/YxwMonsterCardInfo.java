package com.hsr.game.yxw.info;

import com.hsr.game.common.LongIdInfoIF;
import com.hsr.game.yxw.common.em.card.YxwMonsterElement;
import com.hsr.game.yxw.common.em.card.YxwMonsterRace;
import com.hsr.game.yxw.common.em.card.YxwMonsterType;

import java.util.Set;

/**
 * 怪兽卡配置
 */
public class YxwMonsterCardInfo implements LongIdInfoIF {

    /** 唯一id */
    private Long id;
    /** 怪兽属性 */
    private YxwMonsterElement yxwMonsterElement;
    /** 怪兽种族 */
    private YxwMonsterRace yxwMonsterRace;
    /** 怪兽类型 */
    private YxwMonsterType yxwMonsterBaseType;
    /** 等级 */
    private Byte level;
    /** 阶级 */
    private Byte step;
    /** 攻击力 */
    private Integer atk;
    /** 防御力 */
    private Integer def;
    /** 效果id集合 */
    private Set<Integer> effectIds;
    /** 基本信息id */
    private Long baseInfoId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public YxwMonsterElement getYxwMonsterElement() {
        return yxwMonsterElement;
    }

    public void setYxwMonsterElement(YxwMonsterElement yxwMonsterElement) {
        this.yxwMonsterElement = yxwMonsterElement;
    }

    public YxwMonsterRace getYxwMonsterRace() {
        return yxwMonsterRace;
    }

    public void setYxwMonsterRace(YxwMonsterRace yxwMonsterRace) {
        this.yxwMonsterRace = yxwMonsterRace;
    }

    public YxwMonsterType getYxwMonsterBaseType() {
        return yxwMonsterBaseType;
    }

    public void setYxwMonsterBaseType(YxwMonsterType yxwMonsterBaseType) {
        this.yxwMonsterBaseType = yxwMonsterBaseType;
    }

    public Byte getLevel() {
        return level;
    }

    public void setLevel(Byte level) {
        this.level = level;
    }

    public Byte getStep() {
        return step;
    }

    public void setStep(Byte step) {
        this.step = step;
    }

    public Integer getAtk() {
        return atk;
    }

    public void setAtk(Integer atk) {
        this.atk = atk;
    }

    public Integer getDef() {
        return def;
    }

    public void setDef(Integer def) {
        this.def = def;
    }

    public Set<Integer> getEffectIds() {
        return effectIds;
    }

    public void setEffectIds(Set<Integer> effectIds) {
        this.effectIds = effectIds;
    }

    public Long getBaseInfoId() {
        return baseInfoId;
    }

    public void setBaseInfoId(Long baseInfoId) {
        this.baseInfoId = baseInfoId;
    }
}
