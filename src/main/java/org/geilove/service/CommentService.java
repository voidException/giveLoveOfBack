package org.geilove.service;
import org.geilove.pojo.DiscussReply;
public interface CommentService {
	
	public Integer addComment(DiscussReply discussReply);
	
	public Integer delComment(Long commentid);

}
