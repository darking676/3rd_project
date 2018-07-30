package com.bit.shop01.product;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;

public class ProductDaoImpl implements ProductDao {

	@Inject
	SqlSession sqlSession;

	@Override
	public List<ProductVo> listPro() throws Exception {
		return sqlSession.selectList("product.listPro");
	}

	@Override
	public ProductVo detailPro(int productNum) throws Exception {
		return sqlSession.selectOne("product.detailPro", productNum);
	}

	@Override
	public void updatePro(ProductVo proVo) throws Exception {
		
	}

	@Override
	public void deletePro(int productNum) throws Exception {
		
	}

}
