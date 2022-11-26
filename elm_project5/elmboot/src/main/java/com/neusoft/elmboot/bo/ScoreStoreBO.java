package com.neusoft.elmboot.bo;

import org.springframework.beans.factory.annotation.Autowired;

import com.neusoft.elmboot.entity.ScoreStoreEntity;
import com.neusoft.elmboot.mapper.ScoreStoreMapper;

public class ScoreStoreBO {
	private Integer scorestoreId;
	private Integer score;
	private String expireDate;
	
	public ScoreStoreBO(ScoreStoreEntity s) {
		this.scorestoreId = s.getScorestoreId();
		this.score = s.getScore();
		this.expireDate = s.getExpireDate();
	}
	
	public Integer getScorestoreId() {
		return scorestoreId;
	}
	public void setScorestoreId(Integer scorestoreId) {
		this.scorestoreId = scorestoreId;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public String getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}
	
	//方法
	//消费一定的积分
	public int useScore(Integer num) {
		if(score>num) {
			this.score -= num;
			return -1;
		}else if(score == num){
			return 0;
		}else {
			return num - score;
		}
	}
}
