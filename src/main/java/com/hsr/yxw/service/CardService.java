package com.hsr.yxw.service;

import com.hsr.yxw.exception.ServiceException;
import com.hsr.yxw.pojo.Card;

import java.util.Collection;

public interface CardService {
    Collection<Card> listAll() throws ServiceException;
}
