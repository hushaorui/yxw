package com.hsr.game.mapper;

import com.hsr.game.common.BaseMapper;
import com.hsr.game.yxw.data.YxwGameDataItem;
import com.hsr.game.yxw.data.YxwGameDataItemQueryVo;
import com.hsr.game.yxw.data.YxwGameDataType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface YxwGameDataItemMapper extends BaseMapper<YxwGameDataItem, YxwGameDataItemQueryVo> {

    /** 根据数据类型查询 */
    List<YxwGameDataItem> selectByDataType(YxwGameDataType dataType);
    /** 根据玩家id查询该玩家的所有数据 */
    List<YxwGameDataItem> selectByUserId(long userId);
    /** 根据玩家id和数据的类型查询 */
    YxwGameDataItem selectByUserIdAndType(long userId, YxwGameDataType dataType);
    int countByUserIdAndType(long userId, YxwGameDataType dataType);

    /** 根据玩家id删除该玩家的所有数据 */
    void deleteByUserId(long userId);
}
