package com.hsr.yxw.pojo;

import com.hsr.yxw.em.DLRareLevel;
import com.hsr.yxw.em.MonsterElement;
import com.hsr.yxw.em.MonsterRace;
import com.hsr.yxw.em.MonsterType;

public class MonsterCard extends Card {
    private static final long serialVersionUID = 0L;

    /** 怪兽属性 */
    private MonsterElement monsterElement;
    /** 怪兽种族 */
    private MonsterRace monsterRace;
    /** 怪兽类型 */
    private MonsterType monsterType;
    /** 等级 */
    private Byte level;
    /** 在决斗链接的稀有度 */
    private DLRareLevel dlRareLevel;
    /** 攻击力 */
    private Integer atk;
    /** 防御力 */
    private Integer def;

    public MonsterElement getMonsterElement() {
        return monsterElement;
    }

    public void setMonsterElement(MonsterElement monsterElement) {
        this.monsterElement = monsterElement;
    }

    public MonsterRace getMonsterRace() {
        return monsterRace;
    }

    public void setMonsterRace(MonsterRace monsterRace) {
        this.monsterRace = monsterRace;
    }

    public Byte getLevel() {
        return level;
    }

    public void setLevel(Byte level) {
        this.level = level;
    }

    public DLRareLevel getDlRareLevel() {
        return dlRareLevel;
    }

    public void setDlRareLevel(DLRareLevel dlRareLevel) {
        this.dlRareLevel = dlRareLevel;
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

    public MonsterType getMonsterType() {
        return monsterType;
    }

    public void setMonsterType(MonsterType monsterType) {
        this.monsterType = monsterType;
    }
}
