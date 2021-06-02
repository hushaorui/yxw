package com.hsr.game.yxw.service;

import com.hsr.game.yxw.data.YxwGameDataItem;
import com.hsr.game.yxw.data.YxwGameDataType;
import com.hsr.game.exception.ServiceException;

import java.util.List;

public interface YxwGameDataItemService {

    List<YxwGameDataItem> selectByUserId(long userId);

    YxwGameDataItem selectByUserIdAndType(long userId, YxwGameDataType dataType);

    void insert(YxwGameDataItem yxwGameDataItem) throws ServiceException;

    void update(YxwGameDataItem yxwGameDataItem);
}
