package com.bit.shop01.product;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductServiceImpl implements ProductService{
	
//	private ProMapper proMap;
	
//	private PagingHelper pagingHelper;
	
	public ArrayList<ProductVo> getProductList(String procd) throws Exception {
		HashMap<String, String> hashmap = new HashMap<String, String>();
		hashmap.put("procd", procd);
		
//		return proMap.getProductList(hashmap);
		return null;
	}

	public String getProductName(String procd) throws Exception {
//		return proMap.getProductName(procd);
		return null;
	}

	@Override
	public void insert(ProductVo proVo) throws Exception {
//		proMap.insertPro(proVo);
	}

	@Override
	public void update(ProductVo proVo) throws Exception {
//		proMap.updatePro(proVo);
	}

	@Override
	public void delete(int productNum) throws Exception {
//		proMap.deletePro(productNum);
		
	}

	@Override
	public void insertFile(String url) throws Exception {
//		proMap.getFile(url);
	}
	
}
