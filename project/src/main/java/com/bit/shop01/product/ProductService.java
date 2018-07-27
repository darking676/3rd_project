package com.bit.shop01.product;

import java.util.ArrayList;
import java.util.HashMap;

import com.bit.shop01.bbs.AttachFile;

public interface ProductService {

	public ArrayList<ProductVo> getProList(HashMap<String, String> hashmap, String procd) throws Exception;
	
	public void insert(ProductVo proVo) throws Exception;
	
	public void update(ProductVo proVo) throws Exception;
	
	public void delete(int productNum) throws Exception;

	public void insertFile(AttachFile file) throws Exception;
	
/****************************************************************************************************************/
	
	public static String getProductName(String procd) throws Exception {
		return null;
	}
	
	public static ArrayList<AttachFile> getAttachFileList(int attachfileno) {
		return null;
	}

	public static Integer getListNo() {
		return null;
	}

	public static ProductVo selectOne(int productNum) {
		return null;
	}

	public static ArrayList<ProductVo> getList(String procd) {
		return null;
	}

	public static ProductVo getProductVo(int productNum) {
		return null;
	}
	
}
