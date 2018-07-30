package com.bit.shop01.service;

import java.util.Map;

import com.bit.shop01.model.entity.MemVo;

public interface MemService {

	public int insert(MemVo memVo);

	public MemVo login(String memId, String memPassword);

	public int modify(MemVo memVo);

	public void changePasswd(MemVo memVo);

	public static int chkDupId(MemVo memVo) {
		return 0;
	}

	public static int chkSignId(String id) {
		// TODO Auto-generated method stub
		return 0;
	}
}
