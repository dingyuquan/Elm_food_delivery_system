package com.neusoft.elmboot.vo;

import com.neusoft.elmboot.bo.ScoreBO;

public class ScoreVO {
	private String userId = "";
	private Integer channelId = null;
	private Integer eventId = null;
	private Integer credit;
	private String createDate;
	private String expireDate;
	private Integer time = null;
	
	public ScoreVO() {
		
	}
	
	public ScoreVO(ScoreBO s) {
		this.credit = s.getCredit();
		this.createDate = s.getCreateDate();
		this.expireDate = s.getExpireDate();
		this.channelId = s.getChannelId();
		this.eventId = s.getEventId();
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public Integer getChannelId() {
		return channelId;
	}
	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}
	public Integer getEventId() {
		return eventId;
	}
	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}
	public Integer getCredit() {
		return credit;
	}
	public void setCredit(Integer credit) {
		this.credit = credit;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}
	
	
	
}
