package com.bit.shop01.product;

public interface ProductService {

	public static String getProductName(String procd) throws Exception {
		return null;
	}
	
	public void insert(ProductVo proVo) throws Exception;
	
	public void update(ProductVo proVo) throws Exception;
	
	public void delete(int productNum) throws Exception;

	public void insertFile(String url) throws Exception;
	
}
