package com.hsr.game.yxw.service;

import com.hsr.game.yxw.common.YxwCardQueryVo;
import com.hsr.game.yxw.info.YxwCardBaseInfo;
import com.hsr.game.common.PageBean;

public interface YxwCardService {

    PageBean<YxwCardBaseInfo> getYxwCardPageBean(Integer pageNum, Integer pageSize, YxwCardQueryVo vo);

    void deleteYxwCards(String ids);
}
