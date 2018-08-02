package com.bit.shop01.cart;

import java.util.HashMap;
import java.util.List;

import com.bit.shop01.cart.model.entity.CartVo3;

public interface CartService {
	
//	public int test();
	
	//��ٱ��Ͽ� �߰�
	public int insert(CartVo3 vo);

	//��ٱ� ��ǰ Ȯ��
	public int countCart(HashMap<String, Object> params);
//	public int countCart(int productNum, String memId);

	//��ٱ��� ����
	public int editCart(CartVo3 vo);

	//��ٱ��� ���� ����
	public void updateCart(CartVo3 vo);
	
	//��ٱ��� ���
	public List<CartVo3> listCart(String memId);

	//��ٱ��� �ݾ� �հ�
	public int sumPrice(String memId);

	//��ٱ��� ����
	public void delete(int cartNum);

	
}
