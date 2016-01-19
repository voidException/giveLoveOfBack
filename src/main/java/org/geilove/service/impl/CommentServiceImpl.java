package org.geilove.service.impl;

import javax.annotation.Resource;
import  org.geilove.pojo.DiscussReply;
import  org.geilove.service.CommentService;
import  org.geilove.dao.DiscussReplyMapper;
import org.springframework.stereotype.Service;
//评论成功后，应该对相应推文的评论总数加1，这里没写，以后补上吧。
@Service("comment")
public class CommentServiceImpl implements CommentService{
	@Resource 
	private  DiscussReplyMapper replyMapper;
	@Override
	public Integer addComment(DiscussReply discussReply){
		int err=0;
		try{
	        err=replyMapper.insert(discussReply);
		}catch(Exception e){
			System.out.print(e.getMessage());
		}
		return err;
		
	}
}
