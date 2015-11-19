/*
 * @author aihaitao
 * 这个用来显示首页，但不同于个人主页，它没有粉丝数推文数量等信息。
*/
package org.geilove.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.geilove.pojo.Tweet;
import org.geilove.requestParam.DeleteTweetByKeyParam;
import org.geilove.requestParam.TweetByTweetParam;
import org.geilove.service.MainService;
import org.geilove.vo.TweetListVo;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;
@Controller
@RequestMapping("/weibos")
public class TweetController {
	@Resource
	private MainService mainService;
	
	@RequestMapping(value="/getTweetByUserID")//这个是在可直接获取用户ID时候用
	public  @ResponseBody List<TweetByTweetParam> getTweetByUserID(@RequestBody TweetListVo tweetListVo ){
		Long	 userID=tweetListVo.getUserID();
		Integer  page=tweetListVo.getPage();
		Integer  pageSize=tweetListVo.getPageSize();
		List<TweetByTweetParam> tweetBytweets=new ArrayList<TweetByTweetParam>();		
		Map<String,Object> map=new HashMap<String,Object>();//存放查询的参数
		map.put("userID", userID);
		map.put("page", page);
		map.put("pageSize", pageSize);
		List<Tweet> tweets=mainService.getTweetList(map);//首先取得推文，不带头像，不带转发		
		List<Tweet> tweetlist=new ArrayList<Tweet>(tweets.size());//存放转发推文中的原始推文							
		//这个是用来在sql-where-in中循环传参数用的。MainService中的getTweetByDiffIDs
		List<Long> paramslist=new ArrayList<Long>();
		for(int i=0;i<tweets.size();i++){			
			if(tweets.get(i).equals(2)){//如果等于2，则是转发的推文				
				//取得所有要二次查询的推文的id号，因为他们大多数情况下是不一样的，也有可能一样的，所以用list
				paramslist.add(tweets.get(i).getSourcemsgid());//所有需要二次查询数据库获得转发中的推文ID放在这里								
			}			
		}
		//连接数据库进行查询，返回所有被转发的推文
		tweetlist=mainService.getTweetByDiffIDs(paramslist); //paramslist是所有需要二次查询的推文id组成的列表
		
		/* -------，组合成最终要返回的tweetBytweets------------------------------------------------*/
		for(int i=0;i<tweets.size();i++){
			for(int j=0;j<paramslist.size();j++){
				if(tweets.get(i).getSourcemsgid()==tweetlist.get(j).getTweetid()){
					tweetBytweets.get(i).setTweet(tweetlist.get(j));
				}
			}
		}
		return tweetBytweets;
	}
	/*这个仅仅用来测试，返回一组weibo，以便马上能写Android列表*/
	@RequestMapping(value="/getTweetLists")//这个是在可直接获取用户ID时候用
	public  @ResponseBody List<Tweet> getTweetLists(@RequestBody TweetListVo tweetListVo ){
		Long	 userID=tweetListVo.getUserID();
		Integer  page=tweetListVo.getPage();
		Integer  pageSize=tweetListVo.getPageSize();
		Map<String,Object> map=new HashMap<String,Object>();//存放查询的参数
		map.put("userID", userID);
		map.put("page", page);
		map.put("pageSize", pageSize);
		List<Tweet> tweets=mainService.getTweetList(map);//首先取得推文，不带头像，不带转发
	    return tweets;
	}
	/*这个是删除一条推文，因此需要用户名、密码、推文的ID，要授权才能删除，只做逻辑删除*/
	@RequestMapping(value="/deleteTweetByID")
	public @ResponseBody Integer deleteTweetByID(@RequestBody DeleteTweetByKeyParam delTweetParam){
		//这里先检查用户名和密码是否存在并匹配
		// 然后调用Service，将删除标志更新为2
		Tweet tweet=new Tweet();
		tweet.setDeletetag((byte) 2);
		tweet.setTweetid(delTweetParam.getTweetid());//根据这个推文的ID更新
		Integer updateTag=mainService.updateTweetByKeySelective(tweet);		
		return  updateTag;
		
	}
	
		
}
























