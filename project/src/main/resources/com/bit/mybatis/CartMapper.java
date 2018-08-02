package com.bit.mybatis;

import java.util.HashMap;
import java.util.List;

import com.bit.shop01.cart.model.entity.CartVo3;

public interface CartMapper {
	
//	public int test();
	
	//��ٱ��� �߰�
	public int insert(CartVo3 vo);

	//��ٱ��� ��ǰ Ȯ��
	public int countCart(HashMap<String, Object> params);
//	public int countCart(int productNum, String memId);

	//��ٱ��� ����
	public int eidtCart(CartVo3 vo);

	//��ٱ��� ���
//	public ArrayList<CartVo> listCart(String memId);
	public List<CartVo3> listCart(String memId);

	//��ٱ��� �ݾ� �հ�
	public int sumPrice(String memId);

	//��ٱ��� ����
	public void delete(int cartNum);

	//��ٱ��� ���� ����
	public int updateCart(CartVo3 vo);

}
