package com.hsr.yxw.game.service;

import com.hsr.yxw.common.InitializedConfig;
import com.hsr.yxw.common.WebConstants;
import com.hsr.yxw.game.common.em.card.YxwCardType;
import com.hsr.yxw.game.config.card.YxwCardBaseInfoConfig;
import com.hsr.yxw.game.config.card.YxwMonsterCardInfoConfig;
import com.hsr.yxw.game.config.figure.YxwGameFigureInfoConfig;
import com.hsr.yxw.game.config.goods.YxwGoodsInfoConfig;
import com.hsr.yxw.game.config.language.YxwLanguageConfig;
import com.hsr.yxw.game.info.*;
import com.hsr.yxw.util.ConfigUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * 游戏静态数据管理类（数据来源于配置和代码）
 */
@Service
public class YxwGameInfoManager {
    private static final Log log = LogFactory.getLog(YxwGameInfoManager.class);

    /** 卡面信息配置 */
    private YxwCardBaseInfoConfig yxwCardBaseInfoConfig;
    /** 怪兽卡特有信息配置 */
    private YxwMonsterCardInfoConfig yxwMonsterCardInfoConfig;
    /** 可操作的人物配置 */
    private YxwGameFigureInfoConfig yxwGameFigureInfoConfig;
    /** 语言数据 */
    private YxwLanguageConfig yxwLanguageConfig;
    /** 物品数据 */
    private YxwGoodsInfoConfig yxwGoodsInfoConfig;

    /** 经过整合的一张完整的卡的信息 */
    private Map<Long, YxwGameCard> allCards = new HashMap<>();

    /**
     * 读取yml配置，初始化所有卡牌，在spring容器初始化完成后调用
     */
    public void initYxwCard() {
        // 从配置文件中加载所有配置
        loadAllFromYamlFile();
        // 调度所有的配置，使得
        initConfig();
        // 打印一下所有配置，调试使用
        printAllConfig();
    }

    public YxwCardBaseInfoConfig getYxwCardBaseInfoConfig() {
        return yxwCardBaseInfoConfig;
    }

    private void initConfig() {
        for (Map.Entry<Long, YxwCardBaseInfo> entry : yxwCardBaseInfoConfig.getBaseInfoMap().entrySet()) {
            YxwGameCard yxwGameCard = new YxwGameCard();
            long id = entry.getKey();
            YxwCardBaseInfo baseInfo = entry.getValue();
            yxwGameCard.setBaseInfo(baseInfo);
            if (YxwCardType.MONSTER.equals(baseInfo.getCardType())) {
                YxwMonsterCardInfo monsterCardInfo = yxwMonsterCardInfoConfig.getMonsterInfoMap().get(id);
                if (monsterCardInfo == null) {
                    //throw new RuntimeException(String.format("怪兽卡没有对应的怪兽数据，id：%s", id));
                    log.error(String.format("怪兽卡没有对应的怪兽数据，id：%s", id));
                } else {
                    yxwGameCard.setMonsterCardInfo(monsterCardInfo);
                }
            } else if (YxwCardType.MAGIC_OR_TRAP.equals(baseInfo.getCardType())) {
                // TODO
            } else {

            }

        }
    }

    /**
     * 加载本类中的所有配置
     */
    private void loadAllFromYamlFile() {
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Class<?> fieldType = field.getType();
            InitializedConfig initializedConfig;
            Object configInstance = null;
            if (InitializedConfig.class.isAssignableFrom(fieldType)) {
                // 是配置类
                try {
                    configInstance = ConfigUtils.getYxwConfig(fieldType, false);
                } catch (Exception e) {
                    e.printStackTrace();
                    try {
                        configInstance = fieldType.newInstance();
                        initializedConfig = (InitializedConfig) configInstance;
                        initializedConfig.init();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
            if (configInstance != null) {
                try {
                    field.set(this, configInstance);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 打印所有配置内容
     */
    private void printAllConfig() {
        Yaml yaml = new Yaml();
        StringBuilder stringBuilder = new StringBuilder();
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object fieldValue = field.get(this);
                if (fieldValue != null) {
                    String string = yaml.dumpAsMap(fieldValue);
                    stringBuilder.append(string).append(System.lineSeparator());
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        log.info(stringBuilder.toString());
    }

    /**
     * 根据人物id查找人物
     */
    public YxwGameFigureInfo getFigureInfoCfgById(Long figureId) {
        return yxwGameFigureInfoConfig.getFigureMap().get(figureId);
    }

    public YxwGoodsInfo getYxwGoodsInfoById(Long goodsId) {
        return yxwGoodsInfoConfig.getGoodsMap().get(goodsId);
    }

    /**
     * 获取默认语言的字符串，目前默认语言为 Locale.CHINA
     */
    public String getLanguageString(Long id, String defaultString) {
        return getLanguageString(id, Locale.CHINA, defaultString);
    }

    public String getLanguageString(Long id) {
        return getLanguageString(id, Locale.CHINA, WebConstants.NOT_FOUND);
    }
    /**
     * 根据id查找特定语言的字符串
     * yxwLanguageConfig
     */
    public String getLanguageString(Long id, Locale locale, String defaultString) {
        Map<Long, YxwLanguageInfo> languageMap = yxwLanguageConfig.getLanguageMap();
        YxwLanguageInfo yxwLanguageInfo = languageMap.get(id);
        if (yxwLanguageInfo == null) {
            return defaultString;
        }
        if (Locale.CHINA.equals(locale)) {
            return yxwLanguageInfo.getZh_CN();
        } else if (Locale.TAIWAN.equals(locale)) {
            return yxwLanguageInfo.getZh_TW() == null ? yxwLanguageInfo.getZh_CN() : yxwLanguageInfo.getZh_TW();
        } else if (Locale.ENGLISH.equals(locale)) {
            return yxwLanguageInfo.getZh_TW() == null ? yxwLanguageInfo.getZh_CN() : yxwLanguageInfo.getEn();
        } else {
            return "未知的语言：" + locale.toString();
        }
    }
}
