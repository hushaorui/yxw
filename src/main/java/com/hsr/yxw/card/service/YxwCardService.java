package com.hsr.yxw.card.service;

import com.hsr.yxw.card.common.YxwCardQueryVo;
import com.hsr.yxw.card.pojo.YxwCardBaseInfo;
import com.hsr.yxw.common.PageBean;

public interface YxwCardService {

    PageBean<YxwCardBaseInfo> getYxwCardPageBean(Integer pageNum, Integer pageSize, YxwCardQueryVo vo);

    void deleteYxwCards(String ids);
}
