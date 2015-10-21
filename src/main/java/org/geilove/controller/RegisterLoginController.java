
package org.geilove.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.geilove.pojo.User;
import org.geilove.vo.UserLoginVo;
import org.geilove.service.RegisterLoginService;
import org.geilove.vo.UserRegisterVo;
@Controller
@RequestMapping("/user")
public class RegisterLoginController {
	
	private  Integer USER_EXIST=0;
	private  Integer USER_NOT_EXIST=1;// 用户不存在
	private  Integer USER_PASSWORD_WRONG=2;//用户密码错误
	private  Integer USER_NOT_SAY=3;//用户被禁言
	private  Integer USER_HAS_EXIST=4;//邮箱已经注册，请登录
	
	@Resource
	private RegisterLoginService registerLoginService; 
	

	
	@RequestMapping(value="/login",method=RequestMethod.POST)	
	public @ResponseBody User loginUser(@RequestBody UserLoginVo userLoginVo){
		//这里应该先验证用户邮箱和密码是不是符合要求，避免浪费资源查询数据库
		User user=registerLoginService.userLogin(userLoginVo.getUserEmail());
		if(user==null){
			user.setBackupsix(USER_NOT_EXIST);
		}
		else if(userLoginVo.getUserPassword()==user.getUserpassword()){
			user.setBackupsix(USER_EXIST);
		}
		
		return user;
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public @ResponseBody User registerUser(@RequestBody  UserRegisterVo userRegisterVo){
		//这里假设昵称唯一可用，邮箱可用，两次输入的密码一样
		
		User userRegister=new User();		
		userRegister.setUsernickname(userRegisterVo.getUserNickName());
		userRegister.setUseremail(userRegisterVo.getUserEmail());
		userRegister.setUserpassword(userRegisterVo.getUserPassword());
		userRegister.setPhotoupload((byte) 1);
		userRegister.setNotsay((byte)1);
		userRegister.setCertificatetype((byte)1);
		userRegister.setUsertype((byte)1);
		userRegister.setNotsay((byte)1);
		//执行插入
		int tag=registerLoginService.userRegister(userRegister);
		if(tag==1){
			userRegister.setBackupsix(1);
		}else{
			userRegister.setBackupsix(0);
		}
		
		return userRegister; //这么返回是为了，注册成功立马跳转到主页，和登录时一样。
		
	}
	
	//检验邮箱或者昵称是不是可用
	
	
	
	
		
}








