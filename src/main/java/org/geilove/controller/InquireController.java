package org.geilove.controller;

import javax.annotation.Resource;

import org.geilove.pojo.User;
import org.geilove.service.InquireService;
import org.geilove.vo.InquireVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/inquire")
public class InquireController {
	@Resource
	private InquireService inquireservice;
	
	@RequestMapping(value="/userinfo",method=RequestMethod.POST)
	public @ResponseBody User InquireInfo(@RequestBody InquireVo inquirevo){
		User user;
		try{
			user=inquireservice.LookUp(inquirevo);
			
		}catch(Exception e){
				e.printStackTrace();
				user=new User();
				user.setBackupone("false");		
		}
		return user;
	}

}
