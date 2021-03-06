package com.hsr.game.admin.controller;

import com.alibaba.druid.util.StringUtils;
import com.hsr.game.common.CommonResult;
import com.hsr.game.ws.chat.common.ChatMessageType;
import com.hsr.game.ws.chat.common.PublicChatMessageQueryVo;
import com.hsr.game.ws.chat.pojo.PublicChatMessage;
import com.hsr.game.ws.chat.service.ChatMessageService;
import com.hsr.game.common.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminChatMessageController {

    @Autowired
    private ChatMessageService chatMessageService;

    @RequestMapping(value="admin/chat-message-list")
    public String accountList(Integer pageNum, Integer pageSize, PublicChatMessageQueryVo vo, Model model) throws Exception {
        PageBean<PublicChatMessage> pageBean = chatMessageService.getChatMessagePageBean(pageNum, pageSize, vo);
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("chatMessageQueryVo", vo);
        model.addAttribute("pageSizes", PageBean.pageSizes);

        model.addAttribute("messageTypeMapping", ChatMessageType.getMapping());

        return "admin/chat-message-list";
    }

    @RequestMapping(value = "admin/chat-message-delete")
    @ResponseBody
    public CommonResult deleteAccount(String ids) {
        if (StringUtils.isEmpty(ids)) {
            return CommonResult.error("ID不可为空！");
        }
        try {
            chatMessageService.deleteChatMessages(ids);
            return CommonResult.deleteSuccess();
        } catch (Exception e) {
            return CommonResult.error(e.getMessage());
        }
    }

    @RequestMapping(value = "admin/chat-message-select-content")
    @ResponseBody
    public String selectContentById(Long id) {
        return chatMessageService.selectContentById(id);
    }
}
