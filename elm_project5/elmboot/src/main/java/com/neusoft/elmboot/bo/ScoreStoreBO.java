package com.neusoft.elmboot.bo;

import com.neusoft.elmboot.entity.ScoreStoreEntity;

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
	
}
