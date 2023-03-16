package com.neusoft.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.bo.ScoreBO;
import com.neusoft.po.CommonResult;
import com.neusoft.service.ScoreService;
import com.neusoft.vo.ScoreVO;

@CrossOrigin("*")
@RestController
@RequestMapping("/ScoreController")
public class ScoreController {
	
	@Autowired
	private ScoreService scoreService;
	
	@PostMapping("/gainCredit/{userId}/{credit}/{channelId}")
	public CommonResult<Integer> gainCredit(
			@PathVariable("userId") String userId,
			@PathVariable("credit") Integer credit,
			@PathVariable("channelId") Integer channelId) {
		ScoreVO param = new ScoreVO();
		param.setChannelId(channelId);
		param.setCredit(credit);
		param.setUserId(userId);
		int result = scoreService.saveCredit(param);
		return new CommonResult(200, "success", result);
	}
	
	@PostMapping("/expendCredit/{userId}/{credit}/{channelId}")
	public CommonResult<Integer> expendCredit(
			@PathVariable("userId") String userId,
			@PathVariable("credit") Integer credit,
			@PathVariable("channelId") Integer channelId) {
		ScoreVO param = new ScoreVO();
		param.setChannelId(channelId);
		param.setCredit(credit);
		param.setUserId(userId);
		int result = scoreService.expendCredit(param);
		return new CommonResult(200, "success", result);
	}
	
	@GetMapping("/getCredit/{userId}")
	public CommonResult<Integer> getCredit(@PathVariable("userId") String userId) {
		int result = scoreService.getCreditByUserId(userId);
		return new CommonResult(200, "success", result);
	}
	
	@GetMapping("/listGainLog/{userId}")
	public CommonResult<List<ScoreVO>> listGainLog(@PathVariable("userId") String userId) {
		List<ScoreBO> list = scoreService.listGainScoresByUserId(userId);
		List<ScoreVO> result = new ArrayList<ScoreVO>();
		for(int i=0;i<list.size();i++)
			result.add(new ScoreVO(list.get(i)));
		return new CommonResult(200, "success", result);
	}
	
	@GetMapping("/listUseLog/{userId}")
	public CommonResult<List<ScoreVO>> listUseLog(ScoreVO scoreVO) {
		List<ScoreBO> list = scoreService.listUseScoresByUserId(scoreVO.getUserId());
		List<ScoreVO> result = new ArrayList<ScoreVO>();
		for(int i=0;i<list.size();i++)
			result.add(new ScoreVO(list.get(i)));
		return new CommonResult(200, "success", result);
	}
}
