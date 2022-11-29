package com.neusoft.elmboot.mapper;

import java.math.BigDecimal;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.neusoft.elmboot.domain.VirtualWallet;

@Mapper
public interface VirtualWalletMapper {
	
	@Insert("insert into virtualwallet values (null, #{createTime}, #{balance}, #{userId})")
	public int creatWalletByUserId(VirtualWallet virtualwallet);
	
	@Select("select balance from virtualwallet where userId=#{userId}")
	public BigDecimal getBalanceByUserId(String userId);
	
	@Update("update virtualwallet set balance = balance + #{amount} where userId=#{userId}")
	public int addVirtualWalletByUserId(String userId, BigDecimal amount);
	
	@Update("update virtualwallet set balance = balance - #{amount} where userId=#{userId}")
	public int subtractVirtualWalletByUserId(String userId, BigDecimal amount);
	
	@Select("select walletId from virtualwallet where userId=#{userId}")
	public int getWalletIdByUserId(String userId);
}
