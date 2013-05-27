package com.crm.system.dao;

import com.crm.system.model.Operate;

public interface OperateMapper {
    int deleteByPrimaryKey(String id);

    int insert(Operate record);

    int insertSelective(Operate record);

    Operate selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Operate record);

    int updateByPrimaryKey(Operate record);
}