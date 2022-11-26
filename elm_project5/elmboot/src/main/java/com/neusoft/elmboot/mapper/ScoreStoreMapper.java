package com.neusoft.elmboot.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.neusoft.elmboot.entity.ScoreStoreEntity;

@Mapper
public interface ScoreStoreMapper {

	@Insert("insert into scorestore values(null,#{userId},#{score},#{expireDate})")
	public int saveScoreStore(ScoreStoreEntity scoreStoreEntity);
}
