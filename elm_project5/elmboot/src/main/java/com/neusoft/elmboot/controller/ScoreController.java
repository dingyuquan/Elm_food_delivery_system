package com.neusoft.elmboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.elmboot.service.ScoreService;
import com.neusoft.elmboot.vo.ScoreVO;
import com.neusoft.elmboot.vo.VO;

@RestController
@RequestMapping("/ScoreController")
public class ScoreController {
	
	@Autowired
	private ScoreService scoreService;
	
	@RequestMapping("/gainCredit")
	public int gainCredit(ScoreVO scoreVO) {
		return scoreService.saveCredit(scoreVO);
	}
}
