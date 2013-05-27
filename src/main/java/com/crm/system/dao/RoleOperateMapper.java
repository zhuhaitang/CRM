package com.crm.system.dao;

import com.crm.system.model.RoleOperate;

public interface RoleOperateMapper {
    int deleteByPrimaryKey(String id);

    int insert(RoleOperate record);

    int insertSelective(RoleOperate record);

    RoleOperate selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RoleOperate record);

    int updateByPrimaryKey(RoleOperate record);
}