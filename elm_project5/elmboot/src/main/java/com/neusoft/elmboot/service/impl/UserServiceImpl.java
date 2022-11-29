package com.neusoft.elmboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neusoft.elmboot.mapper.UserMapper;
import com.neusoft.elmboot.po.User;
import com.neusoft.elmboot.service.ScoreService;
import com.neusoft.elmboot.service.UserService;
import com.neusoft.elmboot.service.VirtualWalletService;
import com.neusoft.elmboot.util.CommonUtil;
import com.neusoft.elmboot.vo.ScoreVO;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private VirtualWalletService virtualWalletService;
	
	@Autowired
	private ScoreService scoreService;

	@Override
	public User getUserByIdByPass(User user) {

		return userMapper.getUserByIdByPass(user);
	}

	@Override
	public int getUserById(String userId) {
		return userMapper.getUserById(userId);
	}

	@Override
	public int saveUser(User user) {
		int res = userMapper.saveUser(user);
		virtualWalletService.creatWalletByUserId(user.getUserId());
		ScoreVO scoreVO = new ScoreVO();
		scoreVO.setChannelId(2);
		scoreVO.setCreateDate(CommonUtil.getCurrentDate());
		scoreVO.setExpireDate(CommonUtil.getNextData(30));
		scoreVO.setUserId(user.getUserId());
		scoreVO.setCredit(100);
		scoreService.saveCredit(scoreVO);
		return res;
	}


}