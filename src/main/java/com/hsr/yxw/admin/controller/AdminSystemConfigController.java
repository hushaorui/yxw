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
    @RequestMapping(value = "admin/system-config-update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateSystemConfig(SystemConfig systemConfig) throws Exception {
        if (systemConfig == null || systemConfig.getId() == null) {
            return CommonResult.error("数据不可为空！");
        }
        try {
            if (systemConfig.getClassify() == null || "".equals(systemConfig.getClassify().trim())) {
                return CommonResult.error("类别不可为空！");
            }
            if (systemConfig.getConfigValue() == null || "".equals(systemConfig.getConfigValue().trim())) {
                return CommonResult.error("值不可为空！");
            }
            if (systemConfig.getValueType() == null || "".equals(systemConfig.getValueType().trim())) {
                return CommonResult.error("值类型不可为空！");
            }
            // 更新
            systemConfigService.updateSystemConfig(systemConfig);
            return CommonResult.success("修改配置成功！");
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
            return CommonResult.success("删除成功！");
        } catch (ServiceException e) {
            return CommonResult.error(e.getMessage());
        }
    }
}
