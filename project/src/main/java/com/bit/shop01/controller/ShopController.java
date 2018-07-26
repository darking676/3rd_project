package com.bit.shop01.controller;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bit.shop01.bbs.AttachFile;
import com.bit.shop01.bbs.BbsVo;
import com.bit.shop01.page.WebContants;
import com.bit.shop01.product.ProductService;
import com.bit.shop01.product.ProductVo;

@Controller
public class ShopController {

	@RequestMapping(value = "/bottom/", method = RequestMethod.GET)
	public String bottom() {

		return "/products/botDetail";

	}

	@RequestMapping(value = "/dress/", method = RequestMethod.GET)
	public String dress() {

		return "/products/dreDetail";

	}

	@RequestMapping(value = "/outer/", method = RequestMethod.GET)
	public String outer() {

		return "/products/outDetail";

	}

	@RequestMapping(value = "/sale/", method = RequestMethod.GET)
	public String sale() {

		return "/products/saleDetail";

	}

	@RequestMapping(value = "/shoeacc/", method = RequestMethod.GET)
	public String shoeacc() {

		return "/products/sho&accDetail";

	}

	@RequestMapping(value = "/top/", method = RequestMethod.GET)
	public String top() {

		return "/products/topDetail";

	}


	@RequestMapping(value = "/bottom2/", method = RequestMethod.GET)
	public String bottom2() {

		return "/products2/botDetail2";

	}

	@RequestMapping(value = "/dress2/", method = RequestMethod.GET)
	public String dress2() {

		return "/products2/dreDetail2";

	}

	@RequestMapping(value = "/outer2/", method = RequestMethod.GET)
	public String outer2() {

		return "/products2/outDetail2";

	}

	@RequestMapping(value = "/sale2/", method = RequestMethod.GET)
	public String sale2() {

		return "/products2/saleDetail2";

	}

	@RequestMapping(value = "/shoeacc2/", method = RequestMethod.GET)
	public String shoeacc2() {

		return "/products2/sho&accDetail2";

	}

	@RequestMapping(value = "/top2/", method = RequestMethod.GET)
	public String top2() {

		return "/products2/topDetail2";

	}
	
/*===================================================================================*/
	
	@RequestMapping(value = "/bottom3/", method = RequestMethod.GET)
	public String bottom3() {
		
		return "products3/bot/botList3";
		
	}
	
	@RequestMapping(value = "/dress3/", method = RequestMethod.GET)
	public String dress3() {
		
		return "products3/dre/dreList3";
		
	}
	
	@RequestMapping(value = "/outer3/", method = RequestMethod.GET)
	public String outer3() {
		
		return "products3/out/outList3";
		
	}
	
	@RequestMapping(value = "/sale3/", method = RequestMethod.GET)
	public String sale3() {
		
		return "products3/sale/saleList3";
		
	}
	
	@RequestMapping(value = "/shoeacc3/", method = RequestMethod.GET)
	public String shoeacc3() {
		
		return "products3/sho&acc/sho&accList3";
		
	}
	
	@RequestMapping(value = "/top3/", method = RequestMethod.GET)
	public String top3() {
		
		return "products3/top/topList3";
		
	}

	// @RequestMapping("/outer/")
	// public String outer(Model model,HttpServletRequest req) throws SQLException {
	// return "/products/outDetail";
	// }
	//
	// @RequestMapping("/top/")
	// public String top(Model model,HttpServletRequest req) throws SQLException {
	// return "/products/topDetail";
	// }
	//
	//
	// @RequestMapping("/bottom/")
	// public String bottom(Model model,HttpServletRequest req) throws SQLException
	// {
	// return "/products/botDetail";
	// }
	//
	//
	// @RequestMapping("/dress/")
	// public String dress(Model model,HttpServletRequest req) throws SQLException {
	// return "/products/dreDetail";
	// }
	//
	//
	// @RequestMapping("/shoeacc/")
	// public String shoeacc(Model model,HttpServletRequest req) throws SQLException
	// {
	// return "/products/sho&accDetail";
	// }
	//
	//
	// @RequestMapping("/sale/")
	// public String sale(Model model,HttpServletRequest req) throws SQLException {
	// return "/products/saleDetail";
	// }
	//
	//

}
