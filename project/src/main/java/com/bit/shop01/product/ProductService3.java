package com.bit.shop01.product;

import java.util.List;

public interface ProductService3 {

	public List<ProductVo> listPro() throws Exception;
	
	public static List<ProductVo> listPro2() throws Exception { return null; }
	
	public ProductVo detailPro(int productNum) throws Exception;
	
	public static ProductVo detailPro2(int productNum) throws Exception { return null; }
	
	public void updatePro(ProductVo proVo) throws Exception;
	
	public void deletePro(int productNum) throws Exception;
	
}
