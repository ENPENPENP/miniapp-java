package com.elphen.miniapp.api.mapper;

import com.elphen.miniapp.domain.entity.TUserGroup;
import org.apache.ibatis.annotations.Param;

public interface TUserGroupMapper {
    int deleteByPrimaryKey(@Param("id") Integer id, @Param("userId") Integer userId, @Param("groupId") String groupId);

    int insert(TUserGroup record);

    int insertSelective(TUserGroup record);

    TUserGroup selectByUserIdAndGroupId(@Param("userId") Integer userId, @Param("groupId") String groupId);
}