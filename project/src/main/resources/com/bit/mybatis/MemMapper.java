package com.bit.mybatis;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

import com.bit.shop01.model.entity.MemVo;

public interface MemMapper {

	public int insert(MemVo memVo);

	public MemVo login(@Param("memId") String memId, @Param("memPassword") String memPassword);

	public int modify(MemVo memVo);

	public void changePasswd(MemVo memVo);
	
	public Map<Object, Object> idcheck(@RequestBody String memId);

}
