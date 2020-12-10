package com.hsr.yxw.game.service;

import com.hsr.yxw.common.InitializedConfig;
import com.hsr.yxw.game.common.em.card.YxwCardType;
import com.hsr.yxw.game.config.YxwCardBaseInfoConfig;
import com.hsr.yxw.game.config.YxwGameFigureInfoConfig;
import com.hsr.yxw.game.config.YxwMonsterCardInfoConfig;
import com.hsr.yxw.game.info.YxwCardBaseInfo;
import com.hsr.yxw.game.info.YxwGameCard;
import com.hsr.yxw.game.info.YxwGameFigure;
import com.hsr.yxw.game.info.YxwMonsterCardInfo;
import com.hsr.yxw.util.ConfigUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 游戏静态数据管理类（数据来源于配置和代码）
 */
@Service
public class YxwGameInfoManager {
    private static final Log log = LogFactory.getLog(YxwGameInfoManager.class);

    private YxwCardBaseInfoConfig yxwCardBaseInfoConfig;
    private YxwMonsterCardInfoConfig yxwMonsterCardInfoConfig;

    private YxwGameFigureInfoConfig yxwGameFigureInfoConfig;

    private Map<Long, YxwGameCard> allCards = new HashMap<>();
    private Map<Long, YxwGameFigure> allFigures = new HashMap<>();

    /**
     * 读取yml配置，初始化所有卡牌，在spring容器初始化完成后调用
     */
    public void initYxwCard() {
        // 从配置文件中加载所有配置
        loadAllFromYamlFile();
        // 调度所有的配置，使得
        initConfig();
        // 打印以下所有配置
        printAllConfig();
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
                    throw new RuntimeException(String.format("怪兽卡没有对应的怪兽数据，id：%s", id));
                }
                yxwGameCard.setMonsterCardInfo(monsterCardInfo);
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
    public YxwGameFigure findFigureById(Long figureId) {
        return allFigures.get(figureId);
    }
}
