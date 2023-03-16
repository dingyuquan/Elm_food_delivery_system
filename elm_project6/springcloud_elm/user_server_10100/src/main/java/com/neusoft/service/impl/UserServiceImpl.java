package com.neusoft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neusoft.mapper.UserMapper;
import com.neusoft.po.User;
import com.neusoft.service.ScoreService;
import com.neusoft.service.UserService;
import com.neusoft.util.CommonUtil;
import com.neusoft.vo.ScoreVO;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
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