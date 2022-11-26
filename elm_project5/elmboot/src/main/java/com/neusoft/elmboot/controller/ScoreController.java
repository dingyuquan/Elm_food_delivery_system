package com.neusoft.elmboot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.elmboot.bo.ScoreBO;
import com.neusoft.elmboot.service.ScoreService;
import com.neusoft.elmboot.vo.ScoreVO;

@RestController
@RequestMapping("/ScoreController")
public class ScoreController {
	
	@Autowired
	private ScoreService scoreService;
	
	@RequestMapping("/gainCredit")
	public int gainCredit(ScoreVO scoreVO) {
		return scoreService.saveCredit(scoreVO);
	}
	
	@RequestMapping("/expendCredit")
	public int expendCredit(ScoreVO scoreVO) {
		return scoreService.expendCredit(scoreVO);
	}
	
	@RequestMapping("/getCredit")
	public int getCredit(ScoreVO scoreVO) {
		return scoreService.getCreditByUserId(scoreVO.getUserId());
	}
	
	@RequestMapping("/listLog")
	public List<ScoreVO> listLog(ScoreVO scoreVO) {
		List<ScoreBO> list = scoreService.listScoresByUserId(scoreVO.getUserId());
		List<ScoreVO> log = new ArrayList<ScoreVO>();
		for(int i=0;i<list.size();i++)
			log.add(new ScoreVO(list.get(i)));
		return log;
	}
}
