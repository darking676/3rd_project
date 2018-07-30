package com.bit.mybatis;

import java.util.ArrayList;
import java.util.HashMap;

import com.bit.shop01.bbs.AttachFile;
import com.bit.shop01.product.ProductVo;

public interface ProMapper {

	public ArrayList<ProductVo> getProList(HashMap<String, String> hashmap) throws Exception;

	public String getProductName(String procd) throws Exception;

	public void insertPro(ProductVo proVo) throws Exception;

	public void updatePro(ProductVo proVo) throws Exception;

	public void deletePro(int productNum) throws Exception;

	public void getFile(AttachFile file) throws Exception;
	
}
