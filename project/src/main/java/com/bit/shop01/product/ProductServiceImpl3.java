package com.bit.shop01.product;

import java.util.List;

import javax.inject.Inject;

public class ProductServiceImpl3 implements ProductService3 {
	
	@Inject
	ProductDao proDao;

	@Override
	public List<ProductVo> listPro() throws Exception {
		return proDao.listPro();
	}
	
	public List<ProductVo> listPro2() throws Exception {
		return proDao.listPro();
	}

	@Override
	public ProductVo detailPro(int productNum) throws Exception {
		return proDao.detailPro(productNum);
	}
	
	public ProductVo detailPro2(int productNum) throws Exception {
		return proDao.detailPro(productNum);
	}

	@Override
	public void updatePro(ProductVo proVo) throws Exception {
		
	}

	@Override
	public void deletePro(int productNum) throws Exception {
		
	}

}
