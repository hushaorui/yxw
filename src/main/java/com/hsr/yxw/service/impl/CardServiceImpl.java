package com.hsr.yxw.service.impl;

import com.hsr.yxw.config.YxwCardConfig;
import com.hsr.yxw.exception.ServiceException;
import com.hsr.yxw.pojo.Card;
import com.hsr.yxw.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

@Service
public class CardServiceImpl implements CardService {
    @Autowired
    private YxwCardConfig yxwCardConfig;

    @Override
    public Collection<Card> listAll() throws ServiceException {
        return yxwCardConfig.cards.values();
    }
}
