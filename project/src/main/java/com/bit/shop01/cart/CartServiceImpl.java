package com.bit.shop01.cart;

import java.util.List;

import javax.inject.Inject;

import com.bit.shop01.cart.model.CartDao;
import com.bit.shop01.cart.model.entity.CartVo;

public class CartServiceImpl implements CartService {

	@Inject
	CartDao cartDao;
	
	//��ٱ��Ͽ� �߰�
	public void insert(CartVo vo) {
		cartDao.insert(vo);
	}

	//��ٱ��Ͽ��� ��ǰ Ȯ��
	public int countCart(int productNum, String memId) {
		return cartDao.countCart(productNum, memId);
	}

	//��ٱ��� ����
	public void updateCart(CartVo vo) {
		cartDao.updateCart(vo);
	}

	//��ٱ��� ���
	public List<CartVo> listCart(String memId) {
		return cartDao.listCart(memId);
	}

	//��ٱ��� �ݾ� �հ�
	public int sumPrice(String memId) {
		return cartDao.sumPrice(memId);
	}

	//��ٱ��� ����
	public void delete(int cartNum) {
		cartDao.delete(cartNum);
	}

	//��ٱ��� ��ǰ ���� ����
	@Override
	public void updateCart2(CartVo vo) {
		cartDao.updateCart2(vo);
	}

	
}
