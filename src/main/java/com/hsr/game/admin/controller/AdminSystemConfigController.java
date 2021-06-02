package com.hsr.game.admin.controller;

import com.alibaba.druid.util.StringUtils;
import com.hsr.game.common.CommonResult;
import com.hsr.game.common.PageBean;
import com.hsr.game.exception.ServiceException;
import com.hsr.game.sysconfig.common.SystemConfigUtils;
import com.hsr.game.sysconfig.pojo.SystemConfig;
import com.hsr.game.sysconfig.service.SystemConfigService;
import com.hsr.game.sysconfig.common.SystemConfigQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AdminSystemConfigController {

    @Autowired
    private SystemConfigService systemConfigService;

    @RequestMapping(value="admin/system-config-list")
    public String systemConfigList(Integer pageNum, Integer pageSize, SystemConfigQueryVo vo, Model model) throws Exception {
        PageBean<SystemConfig> pageBean = systemConfigService.getSystemConfigPageBean(pageNum, pageSize, vo);
        List<String> classifyList = systemConfigService.selectAllClassify();
        List<String> valueTypeList = systemConfigService.selectAllValueType();
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("classifyList", classifyList);
        model.addAttribute("valueTypeList", valueTypeList);
        model.addAttribute("systemConfigQueryVo", vo);
        model.addAttribute("pageSizes", PageBean.pageSizes);
        return "admin/system-config-list";
    }
    @RequestMapping(value = "admin/system-config-add", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult addSystemConfig(SystemConfig systemConfig) {
        if (systemConfig == null) {
            return CommonResult.dataCannotBeNullError();
        }
        try {
            //校验
            CommonResult commonResult = SystemConfigUtils.checkSystemConfig(systemConfig);
            if (commonResult != null) {
                return commonResult;
            }
            systemConfigService.addSystemConfig(systemConfig);
            return CommonResult.addSuccess();
        } catch (ServiceException e) {
            return CommonResult.error(e.getMessage());
        }
    }
    @RequestMapping(value = "admin/system-config-update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateSystemConfig(SystemConfig systemConfig) throws Exception {
        if (systemConfig == null || systemConfig.getId() == null) {
            return CommonResult.dataCannotBeNullError();
        }
        try {
            CommonResult commonResult = SystemConfigUtils.checkSystemConfig(systemConfig);
            if (commonResult != null) {
                return commonResult;
            }
            // 更新
            systemConfigService.updateSystemConfig(systemConfig);
            return CommonResult.updateSuccess();
        } catch (ServiceException e) {
            return CommonResult.error(e.getMessage());
        }
    }
    @RequestMapping(value = "admin/system-config-delete")
    @ResponseBody
    public CommonResult deleteSystemConfig(String ids) throws Exception {
        if (StringUtils.isEmpty(ids)) {
            return CommonResult.error("ID不可为空！");
        }
        try {
            systemConfigService.deleteSystemConfigs(ids);
            return CommonResult.deleteSuccess();
        } catch (ServiceException e) {
            return CommonResult.error(e.getMessage());
        }
    }
}
