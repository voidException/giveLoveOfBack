package org.geilove.service.impl;

import javax.annotation.Resource;

import org.geilove.dao.UserMapper;
import org.geilove.pojo.User;
import org.geilove.service.InquireService;
import org.geilove.vo.InquireVo;

public class InquireServiceImpl implements InquireService{
	
	@Resource
	private UserMapper usermapper;
	
	@Override
	public User LookUp(InquireVo lookup) {
		User user=usermapper.selectByPrimaryKey(lookup.getUserid());
		return user;
	}



}
