package com.bit.shop01.product.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.bit.shop01.bbs.AttachFile;
import com.bit.shop01.error.ProductNotFoundException;
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

		// list page에서 보여줄 title image
		// file upload1
		List<MultipartFile> file1 = mpRequest.getFiles("upload1");
		for (MultipartFile mf : file1) {
			String filename = mf.getOriginalFilename();
			mf.transferTo(new File(WebContants.BASE_PATH + filename));
		}
		
		// insert file data
		int size1 = file1.size();
		for (int i = 0; i < size1; i++) {
			MultipartFile mpFile = file1.get(i);
			AttachFile attachFile = new AttachFile();
			String filename = mpFile.getOriginalFilename();
			attachFile.setFilename(filename);
			attachFile.setFiletype(mpFile.getContentType());
			attachFile.setFilesize(mpFile.getSize());
		}
		
/////////////////////////////////////////////////////////////////////////////////////
		
		// detail page에서 보여줄 images
		// file upload2
		List<MultipartFile> file2 = mpRequest.getFiles("upload2");
		for (MultipartFile mf : file2) {
			String filename = mf.getOriginalFilename();
			mf.transferTo(new File(WebContants.BASE_PATH + filename));
		}
		
		int size2 = file2.size();		
		for (int j = 0; j < size2; j++) {
			MultipartFile mpFile = file2.get(j);
			AttachFile attachFile = new AttachFile();
			String filename = mpFile.getOriginalFilename();
			attachFile.setFilename(filename);
			attachFile.setFiletype(mpFile.getContentType());
			attachFile.setFilesize(mpFile.getSize());
		}

		return "redirect:/bottom3/";
	}
	
	// detail page
	@RequestMapping(value = "/bottomV3/")
//	public ModelAndView bottomView3(@RequestParam(value="productNum", required = true) int productNum) throws Exception {
		
	public String bottomView3(int productNum, String procd, Model model) throws Exception {
		
//	public String bottomView3(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		/*
		// file path = url + name
		String path = req.getParameter("fileUrl")+req.getParameter("fileName");
		
		String uploadPath = "C:\\gitK2\\project\\src\\main\\webapp\\resources\\imgs3\\";
		
		try {
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(uploadPath+path));
			ByteArrayOutputStream byteStream = new ByteArrayOutputStream(512);
			int imgByte;
			while((imgByte = in.read())!=-1) {
				byteStream.write(imgByte);
			}
			resp.setContentType("imgs3/*");
			byteStream.writeTo(resp.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//////////////////////////////////////////////////////////////////////////////////
		
		// path
		File dir = new File("C:\\gitK2\\project\\src\\main\\webapp\\resources\\imgs3"); 

		File[] fileList = dir.listFiles();

			for(int i = 0 ; i < fileList.length ; i++){
				File file = fileList[i]; 
			}
			
			// 상세보기
			ProductVo proVo = new ProductVo();
			int attachfileno = 0;
			ArrayList<AttachFile> attachFileList = ProductService.getAttachFileList(attachfileno);

//			ArrayList<BbsVo> list = ProductService.getProList(HashMap<String, String>, procd);
			String proName = ProductService.getProductName(procd);
			Integer no = ProductService.getListNo();

			model.addAttribute("plist", proVo);
			model.addAttribute("attachFileList", attachFileList);

//			model.addAttribute("list", list);
			model.addAttribute("proName", proName);
			model.addAttribute("procd", procd);

			model.addAttribute("no", no);
		 	*/
		
		
//		ModelAndView mav = new ModelAndView();
		
//		ProductVo proVo = ProductService.selectOne(productNum);
		ProductVo proVo = ProductService.getProductVo(productNum); 
		
		if(proVo == null) throw new ProductNotFoundException("상품이 존재하지 않음 : "+productNum);
		
		ArrayList<ProductVo> list = new ArrayList<ProductVo>();
		String productName = ProductService.getProductName(procd);
		
		model.addAttribute("list", list);
		model.addAttribute("proName", productName);
		model.addAttribute("procd", procd);
		
//		mav.addObject("proVo", proVo);
//		mav.setViewName("/bot/botDetail3");
		
//		return mav;
		return "products3/bot/botDetail3";
	}
	
	@RequestMapping(value="/edit3/", method = RequestMethod.POST)
	public ModelAndView botEdit(@RequestParam(value = "productNum", required = true) int productNum) throws Exception {
		ModelAndView mav = new ModelAndView();
		ProductVo proVo = ProductService.selectOne(productNum);

		if(proVo == null) throw new ProductNotFoundException("상품이 존재하지 않음 : "+productNum);
		
		mav.addObject("proVo", proVo);
		mav.setViewName("/bot/botUpdate3");
		
		return mav;
	}
}
