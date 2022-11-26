package com.neusoft.elmboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.neusoft.elmboot.entity.ScoreEntity;
import com.neusoft.elmboot.entity.ScoreStoreEntity;

@Mapper
public interface ScoreMapper {

	@Insert("insert into score values(null,#{userId},#{channelId},#{eventId},#{credit},#{createDate},#{expireDate})")
	public int saveScore(ScoreEntity scoreEntity);
	
	@Select("select * from score where userId=#{userId} order by createDate")
	public List<ScoreEntity> listScoreByUserId(String userId);
}
