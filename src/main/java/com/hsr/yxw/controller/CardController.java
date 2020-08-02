package com.hsr.yxw.controller;

import com.hsr.yxw.exception.ServiceException;
import com.hsr.yxw.pojo.Card;
import com.hsr.yxw.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
public class CardController {
    @Autowired
    private CardService cardService;

    @RequestMapping(value = {"card/list", "card"})
    public String cardList(Model model) {
        Collection<Card> cards = null;
        try {
            cards = cardService.listAll();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        model.addAttribute("cards", cards);
        return "card/card-list";
    }
}
