package com.hsr.yxw.game.service;

import com.hsr.yxw.exception.ServiceException;
import com.hsr.yxw.game.data.YxwGameDataItem;
import com.hsr.yxw.game.data.YxwGameDataType;

import java.util.List;

public interface YxwGameDataItemService {

    List<YxwGameDataItem> selectByUserId(long userId);

    YxwGameDataItem selectByUserIdAndType(long userId, YxwGameDataType dataType);

    void insert(YxwGameDataItem yxwGameDataItem) throws ServiceException;

    void update(YxwGameDataItem yxwGameDataItem);
}
