package org.geilove.dao;

import org.geilove.pojo.DiscussReply;

public interface DiscussReplyMapper {
    int deleteByPrimaryKey(Long discussreplyid);

    int insert(DiscussReply record);

    int insertSelective(DiscussReply record);

    DiscussReply selectByPrimaryKey(Long discussreplyid);

    int updateByPrimaryKeySelective(DiscussReply record);

    int updateByPrimaryKey(DiscussReply record);
}