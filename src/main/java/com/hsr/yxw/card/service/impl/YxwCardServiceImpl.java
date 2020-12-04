package com.hsr.yxw.card.service.impl;

import com.hsr.yxw.card.common.YxwCardQueryVo;
import com.hsr.yxw.card.pojo.YxwCard;
import com.hsr.yxw.card.service.YxwCardService;
import com.hsr.yxw.common.PageBean;
import com.hsr.yxw.mapper.YxwCardMapper;
import com.hsr.yxw.util.YxwStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class YxwCardServiceImpl implements YxwCardService {

    private YxwCardMapper yxwCardMapper;
    @Autowired
    public YxwCardServiceImpl(YxwCardMapper yxwCardMapper) {
        this.yxwCardMapper = yxwCardMapper;
    }

    @Override
    public PageBean<YxwCard> getYxwCardPageBean(Integer pageNum, Integer pageSize, YxwCardQueryVo vo) {
        int count = yxwCardMapper.count(vo);
        PageBean<YxwCard> pageBean = new PageBean<>(count, pageSize, pageNum);
        if (vo == null) {
            vo = new YxwCardQueryVo();
        }
        vo.setPagingResult(pageBean.getFirstResult(), pageBean.getPageSize());
        List<YxwCard> list = yxwCardMapper.selectByVo(vo);
        pageBean.setPageList(list, true);
        return pageBean;
    }

    @Override
    public void deleteYxwCards(String ids) {
        ArrayList<Long> idList = YxwStringUtils.idStringToList(ids);
        if (idList.size() > 0) {
            yxwCardMapper.delete(idList);
        }
    }
}
