package com.crm.system.dao;

import com.crm.system.model.OperateType;

public interface OperateTypeMapper {
    int deleteByPrimaryKey(String id);

    int insert(OperateType record);

    int insertSelective(OperateType record);

    OperateType selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OperateType record);

    int updateByPrimaryKey(OperateType record);
}