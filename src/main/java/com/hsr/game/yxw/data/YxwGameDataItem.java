package com.hsr.game.yxw.data;

/**
 * 游戏数据项，一个玩家有多个数据项
 */
public class YxwGameDataItem {
    /** 数据项id，递增 */
    private Long id;
    /** 玩家id */
    private Long userId;
    /** 数据类型 */
    private YxwGameDataType dataType;
    /** 数据的值 fast json格式 */
    private String dateValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public YxwGameDataType getDataType() {
        return dataType;
    }

    public void setDataType(YxwGameDataType dataType) {
        this.dataType = dataType;
    }

    public String getDateValue() {
        return dateValue;
    }

    public void setDateValue(String dateValue) {
        this.dateValue = dateValue;
    }
}
