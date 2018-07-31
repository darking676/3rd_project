package com.bit.shop01.cart.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;

import com.bit.shop01.cart.model.entity.CartVo;

public class CartDaoImpl implements CartDao {

	@Inject
	SqlSession sqlSession;
	
	//��ٱ��� �߰�
	@Override
	public void insert(CartVo vo) {
		sqlSession.insert("cart.listCart", vo);
	}

	//��ٱ��� ��ǰ Ȯ��
	@Override
	public int countCart(int productNum, String memId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("productNum", productNum);
		map.put("memId", memId);
		return sqlSession.selectOne("cart.sumPrice", memId);
	}

	//��ٱ��� ����
	@Override
	public void updateCart(CartVo vo) {
		sqlSession.update("cart.updateCart", vo);
	}

	//��ٱ��� ���
	@Override
	public List<CartVo> listCart(String memId) {
		return sqlSession.selectList("cart.listCart", memId);
	}

	//��ٱ��� �ݾ� �հ�
	@Override
	public int sumPrice(String memId) {
		sqlSession.selectOne("cart.sumPrice", memId);
		return sqlSession.selectOne("cart.sumPrice", memId);
	}

	//��ٱ��� ����
	@Override
	public void delete(int cartNum) {
		sqlSession.delete("cart.deleteCart", cartNum);
	}

	//��ٱ��� ��ǰ ���� ����
	@Override
	public void updateCart2(CartVo vo) {
		sqlSession.update("cart.sumCart", vo);
	}

}
