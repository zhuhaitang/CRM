package com.crm.system.dao;

import com.crm.system.model.RoleModule;

public interface RoleModuleMapper {
    int deleteByPrimaryKey(String id);

    int insert(RoleModule record);

    int insertSelective(RoleModule record);

    RoleModule selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RoleModule record);

    int updateByPrimaryKey(RoleModule record);
}