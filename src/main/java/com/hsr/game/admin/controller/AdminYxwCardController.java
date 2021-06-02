package com.hsr.game.admin.controller;

import com.alibaba.druid.util.StringUtils;
import com.hsr.game.yxw.common.YxwCardQueryVo;
import com.hsr.game.yxw.common.em.card.YxwCardType;
import com.hsr.game.yxw.info.YxwCardBaseInfo;
import com.hsr.game.yxw.service.YxwCardService;
import com.hsr.game.common.CommonResult;
import com.hsr.game.common.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminYxwCardController {

    private YxwCardService yxwCardService;
    @Autowired
    private AdminYxwCardController(YxwCardService yxwCardService) {
        this.yxwCardService = yxwCardService;
    }
    @RequestMapping(value="admin/yxw-card-list")
    public String accountList(Integer pageNum, Integer pageSize, YxwCardQueryVo vo, Model model) throws Exception {
        PageBean<YxwCardBaseInfo> pageBean = yxwCardService.getYxwCardPageBean(pageNum, pageSize, vo);
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("yxwCardQueryVo", vo);
        model.addAttribute("pageSizes", PageBean.pageSizes);

        model.addAttribute("yxwCardTypeMapping", YxwCardType.getMapping());

        return "admin/yxw-card-list";
    }

    @RequestMapping(value = "admin/yxw-card-delete")
    @ResponseBody
    public CommonResult deleteYxwCards(String ids) {
        if (StringUtils.isEmpty(ids)) {
            return CommonResult.error("ID不可为空！");
        }
        try {
            yxwCardService.deleteYxwCards(ids);
            return CommonResult.deleteSuccess();
        } catch (Exception e) {
            return CommonResult.error(e.getMessage());
        }
    }
}
