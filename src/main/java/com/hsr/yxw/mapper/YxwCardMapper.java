package com.hsr.yxw.mapper;

import com.hsr.yxw.card.common.YxwCardQueryVo;
import com.hsr.yxw.card.pojo.YxwCard;
import com.hsr.yxw.common.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface YxwCardMapper extends BaseMapper<YxwCard, YxwCardQueryVo> {

}
