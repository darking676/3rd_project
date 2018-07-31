package com.bit.shop01.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bit.shop01.cart.CartService;
import com.bit.shop01.cart.model.entity.CartVo;

@Controller
public class CartController {

	@RequestMapping(value = "/cart/", method = RequestMethod.GET)
	public String cart(HttpSession session) {
		return "/info/cart";
	}
	
	//��ٱ��� ���
	@RequestMapping(value = "/cart2/", method = RequestMethod.GET)
	public String cart2(HttpSession session) {
		return "/info2/cart2";
	}
	
	//��ٱ��Ͽ� ��ǰ �߰�
	@RequestMapping(value="/insert")
	public String insert(@ModelAttribute CartVo vo, HttpSession session) {
		String memId = (String) session.getAttribute("memId");
		vo.setMemId(memId);
		
		//��ǰ�� �ִ��� �˻�
		int count = CartService.countCart(vo.getProductNum(), memId);
		if (count == 0) {
			//������ update(����)
			CartService.updateCart(vo);
		} else {
			//������ insert
			CartService.insert(vo);
		}
		
		if(count == 0) {
			//������ insert
			CartService.insert(vo);
		} else {
			//������ update(����)
			CartService.updateCart(vo);
		}
		
		return "redirect:/cart2/";
	}
	
	//��ٱ��� ���
	@RequestMapping(value="/list")
	public ModelAndView list(HttpSession session, ModelAndView mav) {
		
		String memId = (String) session.getAttribute("memId");
		Map<String, Object> map = new HashMap<String, Object>();
		List<CartVo> list = CartService.listCart(memId);
		int sumPrice = CartService.sumPrice(memId);
		
		//��۷�(10���� �̻��̸� ���� �ƴϸ� 2500��)
		int fee = sumPrice >= 100000 ? 0 : 2500;
		
		map.put("list", list);				//��ٱ��� ������ map�� ����
		map.put("count", list.size());		//��ٱ��� ��ǰ Ȯ��
		map.put("sumPrice", sumPrice);		//��ٱ��� ��ü �ݾ� �հ�
		map.put("fee", fee);				//��۷�
		map.put("AllSum", sumPrice + fee);	//�ֹ��ݾ� �հ�
		
		mav.setViewName("/info2/cart2/");	//�� ����
		mav.addObject("map", map);			//map���� ����
		
		return mav;
	}
	
	//��ٱ��Ͽ��� ��ǰ ����
	@RequestMapping(value="/delete")
	public String delete(@RequestParam int cartNum) {
		CartService.delete(cartNum);
		return "redirect:/cart2/";
	}
	
	//��ٱ��Ͽ��� ����
	@RequestMapping(value="/update")
	public String update(@RequestParam int[] amount, @RequestParam int[] productNum, HttpSession session) {
		String memId = (String) session.getAttribute("memId");
		for(int i=0; i<productNum.length; i++) {
			CartVo vo = new CartVo();
			vo.setMemId(memId);
			vo.setQuantity(amount[i]);
			vo.setProductNum(productNum[i]);
			CartService.updateCart(vo);
		}
		
		return "redirect:/cart2/";
	}

}
