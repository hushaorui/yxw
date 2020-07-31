package com.hsr.yxw.admin.controller;

import com.alibaba.druid.util.StringUtils;
import com.hsr.yxw.common.CommonResult;
import com.hsr.yxw.common.PageBean;
import com.hsr.yxw.exception.ServiceException;
import com.hsr.yxw.sysconfig.pojo.SystemConfig;
import com.hsr.yxw.sysconfig.service.SystemConfigService;
import com.hsr.yxw.sysconfig.vo.SystemConfigQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminSystemConfigController {

    @Autowired
    private SystemConfigService systemConfigService;

    @RequestMapping(value="admin/system-config-list")
    public String systemConfigList(Integer pageNum, Integer pageSize, SystemConfigQueryVo vo, Model model) throws Exception {
        /*PageBean<SystemConfig> pageBean = systemConfigService.getsystemConfigPageBean(pageNum, pageSize, vo);
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("pageSizes", PageBean.pageSizes);*/
        return "admin/system-config-list";
    }
    @RequestMapping(value = "admin/system-config-delete")
    @ResponseBody
    public CommonResult deleteSystemConfig(String ids) throws Exception {
        /*if (StringUtils.isEmpty(ids)) {
            return CommonResult.danger("ID不可为空！");
        }
        try {
            systemConfigService.deleteSystemConfigs(ids);
            return CommonResult.success("删除成功！");
        } catch (ServiceException e) {
            return CommonResult.danger(e.getMessage());
        }*/
        return null;
    }
}
