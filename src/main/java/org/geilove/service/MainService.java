package org.geilove.service;
/*
 * 这里实现获取首页官网推文的功能，每次10条，这里会根据官网用户ID,标签，每次取得条数进行查找。
*/
import java.util.List;

import org.geilove.pojo.Tweet;



import java.util.Map;

public interface  MainService {
	public List<Tweet> getTweetList(Map<String, Object> map);
	
	public List<Tweet> getTweetByDiffIDs(List<Long> lst);
   
}
