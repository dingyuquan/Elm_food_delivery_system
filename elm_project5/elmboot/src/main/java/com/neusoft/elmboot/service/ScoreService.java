package com.neusoft.elmboot.service;

import java.util.List;

import com.neusoft.elmboot.bo.ScoreBO;
import com.neusoft.elmboot.vo.ScoreVO;

public interface ScoreService {

	public int saveCredit(ScoreVO scoreVO);
	//public int saveCredit(String userId);
	
	public int expendCredit(ScoreVO scoreVO);
	
	public int getCreditByUserId(String userId);
	
	public List<ScoreBO> listGainScoresByUserId(String userId);
	
	public List<ScoreBO> listUseScoresByUserId(String userId);
}
