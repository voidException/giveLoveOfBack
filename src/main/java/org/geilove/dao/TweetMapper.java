package org.geilove.dao;

import java.util.List;
import java.util.Map;

import org.geilove.pojo.Tweet;

public interface TweetMapper {
    int deleteByPrimaryKey(Long tweetid);

    int insert(Tweet record);

    int insertSelective(Tweet record);

    Tweet selectByPrimaryKey(Long tweetid);
    
    List<Tweet> selectByMainKey(Map<String, Object> map); //自定义方法
    
    List<Tweet> findByIdsMap(List<Long> lst); //自定义方法，根据给定的一组不同的tweet的id查找出这组tweet，放在列表中

    int updateByPrimaryKeySelective(Tweet record);

    int updateByPrimaryKey(Tweet record);
}