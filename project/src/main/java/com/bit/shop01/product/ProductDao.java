package com.bit.shop01.product;

import java.util.List;

public interface ProductDao {

	public List<ProductVo> listPro() throws Exception;
	
	public ProductVo detailPro(int productNum) throws Exception;
	
	public void updatePro(ProductVo proVo) throws Exception;
	
	public void deletePro(int productNum) throws Exception;
	
}
