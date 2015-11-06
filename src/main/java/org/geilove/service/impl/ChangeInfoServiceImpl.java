package org.geilove.service.impl;

import javax.annotation.Resource;

import org.geilove.dao.UserMapper;
import org.geilove.pojo.User;
import org.geilove.service.ChangeInfoService;
import org.geilove.vo.ChangeInfoVo;
import org.springframework.stereotype.Service;

@Service("changeinfoservice")
public class ChangeInfoServiceImpl implements ChangeInfoService {

	@Resource
	private UserMapper userMapper;

	@Override
	public int Infomation(ChangeInfoVo modify_user) {
		User user = ToUser(modify_user);
		int affet_row = 0;
		try {
			affet_row = userMapper.updateByPrimaryKeySelective(user);
			if (affet_row == 0) {
				affet_row = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return affet_row;
	}

	@Override
	public User ToUser(ChangeInfoVo modify_user) {
		User user=new User();
		user.setUserid(modify_user.getUserid());
		user.setDigital(modify_user.getDigital());
		user.setUseremail(modify_user.getUseremail());
		user.setUserpassword(modify_user.getUserpassword());
		user.setUsertype(modify_user.getUsertype());
		user.setCertificatetype(modify_user.getCertificatetype());
		user.setRealname(modify_user.getRealname());
		user.setIdentitycard(modify_user.getIdentitycard());
		user.setSex(modify_user.getSex());
		user.setUsertag(modify_user.getUsertag());
		user.setUniversity(modify_user.getUniversity());
		user.setCollegeyear(modify_user.getCollegeyear());
		user.setCollegeyeartwo(modify_user.getCollegeyeartwo());
		user.setUniversitytwo(modify_user.getUniversitytwo());
		user.setCompany(modify_user.getCompany());
		user.setAddress(modify_user.getAddress());
		user.setCountry(modify_user.getCountry());
		user.setRegisterdate(modify_user.getRegisterdate());
		user.setMsgpubcount(modify_user.getMsgpubcount());
		user.setBoxcount(modify_user.getBoxcount());
		user.setFanscount(modify_user.getFanscount());
		user.setFollowcount(modify_user.getFollowcount());
		user.setNotsay(modify_user.getNotsay());
		user.setNotsaytime(modify_user.getNotsaytime());
		user.setCansaytime(modify_user.getCansaytime());
		user.setUserphoto(modify_user.getUserphoto());
		user.setPhotoupload(modify_user.getPhotoupload());
		user.setSelfintroduce(modify_user.getSelfintroduce());
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findUserById(Long userid) {
		// TODO Auto-generated method stub
		User user = userMapper.selectByPrimaryKey(userid);
		return user;
	}

}
