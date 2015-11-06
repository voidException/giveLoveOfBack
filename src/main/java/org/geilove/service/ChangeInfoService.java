package org.geilove.service;

import org.geilove.pojo.User;
import org.geilove.vo.ChangeInfoVo;

public interface ChangeInfoService {
	public int Infomation(ChangeInfoVo modify_user); 
	public User ToUser(ChangeInfoVo modify_user) ;
	public User findUserById(Long userid);
	
}
