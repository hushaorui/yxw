package com.hsr.game.yxw.service.impl;

import com.hsr.game.yxw.common.YxwCardQueryVo;
import com.hsr.game.yxw.config.card.YxwCardBaseInfoConfig;
import com.hsr.game.yxw.info.YxwCardBaseInfo;
import com.hsr.game.yxw.service.YxwCardService;
import com.hsr.game.yxw.service.YxwGameInfoManager;
import com.hsr.game.common.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YxwCardServiceImpl implements YxwCardService {

    private YxwGameInfoManager yxwGameInfoManager;
    @Autowired
    public YxwCardServiceImpl(YxwGameInfoManager yxwGameInfoManager) {
        this.yxwGameInfoManager = yxwGameInfoManager;
    }
    @Override
    public PageBean<YxwCardBaseInfo> getYxwCardPageBean(Integer pageNum, Integer pageSize, YxwCardQueryVo vo) {
        YxwCardBaseInfoConfig yxwCardBaseInfoConfig = yxwGameInfoManager.getYxwCardBaseInfoConfig();
        List<YxwCardBaseInfo> baseInfoList = yxwCardBaseInfoConfig.getBaseInfoList();
        int count = baseInfoList.size();
        PageBean<YxwCardBaseInfo> pageBean = new PageBean<>(count, pageSize, pageNum);
        if (vo == null) {
            vo = new YxwCardQueryVo();
        }
        vo.setPagingResult(pageBean.getFirstResult(), pageBean.getPageSize());
        List<YxwCardBaseInfo> list = yxwCardBaseInfoConfig.getBySort(vo.getFirstResult(), vo.getMaxResult());
        pageBean.setPageList(list, true);
        return pageBean;
    }

    @Override
    public void deleteYxwCards(String ids) {
        // TODO
    }
}
