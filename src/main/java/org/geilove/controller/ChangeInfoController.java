package org.geilove.controller;

import javax.annotation.Resource;

import org.geilove.pojo.User;
import org.geilove.service.ChangeInfoService;
import org.geilove.service.RegisterLoginService;
import org.geilove.vo.ChangeInfoVo;
import org.geilove.vo.UserLoginVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/changeinfo")
public class ChangeInfoController {
	@Resource
	private ChangeInfoService changeinfoservice;

	@RequestMapping(value = "/change", method = RequestMethod.POST)
	public @ResponseBody User changeinfo(@RequestBody ChangeInfoVo changeinfovo) {

		User user ;

		int affect_row = changeinfoservice.Infomation(changeinfovo);
		
		try {
			if (affect_row == 0) {
				user=new User();
				user.setBackupone("false");
			} else {
				user = changeinfoservice.ToUser(changeinfovo);
				user.setBackupone("modify successful");
				return user;
			}

		} catch (Exception e) {
			e.printStackTrace();
			user = new User();
			user.setBackupone("false");
		}
		
		user=changeinfoservice.findUserById(changeinfovo.getUserid());
		

		return user;
	}

}
