package com.bit.shop01.cart;

import java.util.List;

import com.bit.shop01.cart.model.entity.CartVo;

public interface CartService {

	//��ٱ��Ͽ� �߰�
	public static int insert(CartVo vo) {
		return 0;
	}

	//��ٱ� ��ǰ Ȯ��
	public static int countCart(int productNum, String memId) {
		return 0;
	}

	//��ٱ��� ����
	public static int updateCart(CartVo vo) {
		return 0;
	}

	//��ٱ��� ���� ����
	public void updateCart2(CartVo vo);
	
	//��ٱ��� ���
	public static List<CartVo> listCart(String memId) {
		return null;
	}

	//��ٱ��� �ݾ� �հ�
	public static int sumPrice(String memId) {
		return 0;
	}

	//��ٱ��� ����
	public static void delete(int cartNum) {
	}
	
	
}
