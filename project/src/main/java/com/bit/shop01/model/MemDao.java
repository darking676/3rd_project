package com.bit.shop01.model;

import java.util.Map;

public interface MemDao {

	public static int idDuplChk(String memId, Map<String, Object> commandMap) {
		return selectOne(memId, commandMap);
	}

	public static int selectOne(String memId, Map<String, Object> commandMap) {
		return 0;
	}
}
