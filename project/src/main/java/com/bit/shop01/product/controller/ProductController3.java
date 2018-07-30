package com.bit.shop01.product.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bit.shop01.product.ProductService3;

@Controller
public class ProductController3 {
	
	@RequestMapping("/dre3/")
	public ModelAndView dreList3() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("product3/dre/dreList3");
		mav.addObject("list", ProductService3.listPro2());
		return mav;
	}
	
	@RequestMapping("/detail/{productNum}")
	public ModelAndView dreDetail3(@PathVariable("productNum") int productNum) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("product3/dre/dreDetail");
		mav.addObject("proVo", ProductService3.detailPro2(productNum));
		return mav;
	}
}
