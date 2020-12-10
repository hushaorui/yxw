package com.hsr.yxw.game.service.impl;

import com.hsr.yxw.game.common.YxwCardQueryVo;
import com.hsr.yxw.game.info.YxwCardBaseInfo;
import com.hsr.yxw.game.service.YxwCardService;
import com.hsr.yxw.common.PageBean;
import org.springframework.stereotype.Service;

@Service
public class YxwCardServiceImpl implements YxwCardService {


    @Override
    public PageBean<YxwCardBaseInfo> getYxwCardPageBean(Integer pageNum, Integer pageSize, YxwCardQueryVo vo) {

        return null;
    }

    @Override
    public void deleteYxwCards(String ids) {
        // TODO
    }
}
