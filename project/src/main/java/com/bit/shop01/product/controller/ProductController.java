package com.bit.shop01.product.controller;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bit.shop01.bbs.AttachFile;
import com.bit.shop01.page.WebContants;
import com.bit.shop01.product.ProductService;
import com.bit.shop01.product.ProductVo;

@Controller
public class ProductController {
	
	// bottom, dress, outer, sale, sho&acc, top
	// list, write, view(detail), update, delete
	// admin -> write, update, delete
	// member, guest -> list
	
	@RequestMapping(value = "/bottomW3/", method = RequestMethod.GET)
	public String bottomWrite3(String procd, Model model) throws Exception {
		// 상품 이름
		String productName = ProductService.getProductName(procd);
		model.addAttribute("productName", productName);
		return "products3/bot/botWrite3";
	}
	
	@RequestMapping(value = "/bottomW3/", method = RequestMethod.POST)
	public String bottomWrite3(ProductVo proVo, MultipartHttpServletRequest mpRequest) throws Exception {

		List<MultipartFile> fileList = mpRequest.getFiles("upload");
		for (MultipartFile mf : fileList) {
			String filename = mf.getOriginalFilename();
			mf.transferTo(new File(WebContants.BASE_PATH + filename));
		}

		int size = fileList.size();
		for (int i = 0; i < size; i++) {
			MultipartFile mpFile = fileList.get(i);
			AttachFile attachFile = new AttachFile();
			String filename = mpFile.getOriginalFilename();
			attachFile.setFilename(filename);
			attachFile.setFiletype(mpFile.getContentType());
			attachFile.setFilesize(mpFile.getSize());
		}

		return "redirect:/bottom3/";
	}
	
	@RequestMapping(value = "/bottomV3/", method = RequestMethod.GET)
	public String bottomView3() throws Exception {
		
		return "products3/bot/botDetail3/";
	}
}
