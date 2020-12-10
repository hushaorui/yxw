package com.hsr.yxw.game.service.impl;

import com.hsr.yxw.exception.ServiceException;
import com.hsr.yxw.game.data.YxwGameDataItem;
import com.hsr.yxw.game.data.YxwGameDataType;
import com.hsr.yxw.game.service.YxwGameDataItemService;
import com.hsr.yxw.mapper.YxwGameDataItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YxwGameDataItemServiceImpl implements YxwGameDataItemService {

    private final YxwGameDataItemMapper yxwGameDataItemMapper;

    @Autowired
    public YxwGameDataItemServiceImpl(YxwGameDataItemMapper yxwGameDataItemMapper) {
        this.yxwGameDataItemMapper = yxwGameDataItemMapper;
    }

    @Override
    public List<YxwGameDataItem> selectByUserId(long userId) {
        return yxwGameDataItemMapper.selectByUserId(userId);
    }

    @Override
    public YxwGameDataItem selectByUserIdAndType(long userId, YxwGameDataType dataType) {
        return yxwGameDataItemMapper.selectByUserIdAndType(userId, dataType);
    }

    @Override
    public void insert(YxwGameDataItem yxwGameDataItem) throws ServiceException {
        int count = yxwGameDataItemMapper.countByUserIdAndType(yxwGameDataItem.getUserId(), yxwGameDataItem.getDataType());
        if (count > 0) {
            // 同一个玩家数据库中存在有相同类型的数据
            throw new ServiceException("同一个玩家数据库中存在有相同类型的数据，玩家id：%s");
        }
        yxwGameDataItemMapper.insert(yxwGameDataItem);
    }

    @Override
    public void update(YxwGameDataItem yxwGameDataItem) {
        yxwGameDataItemMapper.update(yxwGameDataItem);
    }
}
