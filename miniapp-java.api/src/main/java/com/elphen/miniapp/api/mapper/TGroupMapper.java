package com.elphen.miniapp.api.mapper;

import com.elphen.miniapp.domain.entity.TGroup;
import org.apache.ibatis.annotations.Param;

public interface TGroupMapper {
    int deleteByPrimaryKey(String groupId);

    int insert(TGroup record);

    int insertSelective(TGroup record);

    TGroup selectByPrimaryKey(String groupId);

    int updateByPrimaryKeySelective(TGroup record);

    int updateByPrimaryKey(TGroup record);

    int deleteByPrimaryKey(@Param("groupId") String groupId, @Param("groupOwnerId") Integer groupOwnerId);

    TGroup selectByPrimaryKey(@Param("groupId") String groupId, @Param("groupOwnerId") Integer groupOwnerId);
}