package com.neusoft.mapper;

import java.math.BigDecimal;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.neusoft.domain.VirtualWallet;

@Mapper
public interface VirtualWalletMapper {
	
	@Insert("insert into virtualwallet values (null, #{createTime}, #{balance}, #{userId})")
	public int createWalletByUserId(VirtualWallet virtualwallet);
	
	@Select("select balance from virtualwallet where userId=#{userId}")
	public BigDecimal getBalanceByUserId(String userId);
	
	@Update("update virtualwallet set balance = balance+#{amount} where userId=#{userId}")
	public int addVirtualWalletByUserId(@Param("userId") String userId, @Param("amount") BigDecimal amount);
	
	@Update("update virtualwallet set balance = balance-#{amount} where userId=#{userId}")
	public int subtractVirtualWalletByUserId(@Param("userId") String userId, @Param("amount") BigDecimal amount);
	
	@Select("select walletId from virtualwallet where userId=#{userId}")
	public int getWalletIdByUserId(String userId);
}
