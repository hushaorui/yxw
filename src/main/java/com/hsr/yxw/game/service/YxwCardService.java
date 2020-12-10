package com.hsr.yxw.game.service;

import com.hsr.yxw.game.common.YxwCardQueryVo;
import com.hsr.yxw.game.info.YxwCardBaseInfo;
import com.hsr.yxw.common.PageBean;

public interface YxwCardService {

    PageBean<YxwCardBaseInfo> getYxwCardPageBean(Integer pageNum, Integer pageSize, YxwCardQueryVo vo);

    void deleteYxwCards(String ids);
}
