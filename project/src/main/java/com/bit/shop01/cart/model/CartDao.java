package com.bit.shop01.cart.model;

import java.util.List;

import com.bit.shop01.cart.model.entity.CartVo;

public interface CartDao {

	//��ٱ��� �߰�
	public void insert(CartVo vo);

	//��ٱ��� ��ǰ Ȯ��
	public int countCart(int productNum, String memId);

	//��ٱ��� ����
	public void updateCart(CartVo vo);

	//��ٱ��� ���
	public List<CartVo> listCart(String memId);

	//��ٱ��� �ݾ� �հ�
	public int sumPrice(String memId);

	//��ٱ��� ����
	public void delete(int cartNum);

	//��ٱ��� ���� ����
	public void updateCart2(CartVo vo);

}
