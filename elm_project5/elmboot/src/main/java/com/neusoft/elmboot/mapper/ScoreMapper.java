package com.neusoft.elmboot.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.neusoft.elmboot.entity.ScoreEntity;

@Mapper
public interface ScoreMapper {

	@Insert("insert into score values(null,#{userId},#{channelId},#{eventId},#{credit},#{createDate},#{expireDate})")
	public int saveScore(ScoreEntity scoreEntity);
}
