package com.neusoft.elmboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neusoft.elmboot.entity.ScoreEntity;
import com.neusoft.elmboot.entity.ScoreStoreEntity;
import com.neusoft.elmboot.mapper.ScoreMapper;
import com.neusoft.elmboot.mapper.ScoreStoreMapper;
import com.neusoft.elmboot.service.ScoreService;
import com.neusoft.elmboot.util.CommonUtil;
import com.neusoft.elmboot.vo.ScoreVO;

@Service
public class ScoreServiceImpl implements ScoreService {

	@Autowired
	private ScoreMapper scoreMapper;
	@Autowired
	private ScoreStoreMapper scoreStoreMapper;
	
	@Override
	public int saveCredit(ScoreVO scoreVO) {
		// TODO Auto-generated method stub
		ScoreEntity score = new ScoreEntity();
		score.setUserId(scoreVO.getUserId());
		score.setCredit(scoreVO.getCredit());
		score.setCreateDate(CommonUtil.getCurrentDate());
		//计算过期时间
		score.setExpireDate(CommonUtil.getNextData(scoreVO.getTime()));
		ScoreStoreEntity scoreStoreEntity = new ScoreStoreEntity(score);
		scoreStoreMapper.saveScoreStore(scoreStoreEntity);
		return scoreMapper.saveScore(score);
	}
	
//	@Override
//	public int saveCredit(String userId) {
//		// TODO Auto-generated method stub
//		ScoreEntity score = new ScoreEntity();
//		score.setCredit(777);
//		score.setUserId(userId);
//		score.setCreateDate(CommonUtil.getCurrentDate());
//		score.setExpireDate(CommonUtil.getCurrentDate());
//		//ScoreStoreEntity scoreStoreEntity = new ScoreStoreEntity(score);
//		//scoreStoreMapper.saveScoreStore(scoreStoreEntity);
//		return scoreMapper.saveScore(score);
//	}

}
