package com.bit.shop01.cart.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.bit.mybatis.CartMapper;
import com.bit.shop01.cart.model.entity.CartVo3;

public class CartDaoImpl implements CartMapper {

	@Autowired
	SqlSession sqlSession;
	
	//��ٱ��� �߰�
	@Override
	public int insert(CartVo3 vo) {
		return sqlSession.insert("cart.listCart", vo);
	}

	//��ٱ��� ��ǰ Ȯ��
	@Override
//	public int countCart(int productNum, String memId) {
		public int countCart(HashMap<String, Object> params) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("productNum", productNum);
//		map.put("memId", memId);
		return sqlSession.selectOne("cart.sumPrice", params);
//		return sqlSession.selectOne("cart.sumPrice", memId);
	}

	//��ٱ��� ����
	@Override
	public int editCart(CartVo3 vo) {
		return sqlSession.update("cart.eidtCart", vo);
	}

	//��ٱ��� ���
	@Override
	public List<CartVo3> listCart(String memId) {
		System.out.println("list cart(dao) : " + memId);
		return sqlSession.selectList("cart.listCart", memId);
	}
	
//	public int test() {
//		System.out.println("test start");
//		int result = sqlSession.selectOne("cart.test");
//		System.out.println("test end");
//		return result;
//	}

	//��ٱ��� �ݾ� �հ�
	@Override
	public int sumPrice(String memId) {
//		sqlSession.selectOne("cart.sumPrice", memId);
		System.out.println("dao memid : " + memId);
		return sqlSession.selectOne("cart.sumPrice", memId);
	}

	//��ٱ��� ����
	@Override
	public void delete(int cartNum) {
		sqlSession.delete("cart.deleteCart", cartNum);
	}

	//��ٱ��� ��ǰ ���� ����
	@Override
	public int updateCart(CartVo3 vo) {
		return sqlSession.update("cart.updateCart", vo);
	}


}
