package com.bit.shop01.controller.bbs;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bit.shop01.bbs.AttachFile;
import com.bit.shop01.bbs.BbsService;
import com.bit.shop01.bbs.BbsVo;
import com.bit.shop01.bbs.Comment;
import com.bit.shop01.page.PagingHelper;
import com.bit.shop01.page.WebContants;

@Controller
public class BbsController3 {

	@Autowired
	private BbsService bbsService;

	// notice �Խ��� ����
		@RequestMapping(value = "/rev_bbs", method = { RequestMethod.GET, RequestMethod.POST })
		public String bbs(String bbscd, Integer curPage, String searchWord, Model model) throws Exception {

			if (bbscd == null)
				bbscd = "review";
			if (curPage == null)
				curPage = 1;
			if (searchWord == null)
				searchWord = "";

			int numPerPage = 10;// �������� ���ڵ� �� ����
			int pagePerBlock = 100;// ������ ��ũ�� �׷�(block)�� ũ�� ����

			int totalRecord = bbsService.getTotalRecord(bbscd, searchWord);

			PagingHelper pagingHelper = new PagingHelper(totalRecord, curPage, numPerPage, pagePerBlock);
			bbsService.setPagingHelper(pagingHelper);

			int start = pagingHelper.getStartRecord();
			int end = pagingHelper.getEndRecord();

			ArrayList<BbsVo> list = bbsService.getbbseditList(bbscd, searchWord, start, end);
			String bbsnm = bbsService.getBbsNm(bbscd);
			Integer no = bbsService.getListNo();
			Integer prevLink = bbsService.getPrevLink();
			Integer nextLink = bbsService.getNextLink();
			Integer firstPage = bbsService.getFirstPage();
			Integer lastPage = bbsService.getLastPage();
			int[] pageLinks = bbsService.getPageLinks();

			model.addAttribute("list", list);
			model.addAttribute("bbsnm", bbsnm);
			model.addAttribute("bbscd", bbscd);

			model.addAttribute("no", no);
			model.addAttribute("prevLink", prevLink);
			model.addAttribute("nextLink", nextLink);
			model.addAttribute("firstPage", firstPage);
			model.addAttribute("lastPage", lastPage);
			model.addAttribute("pageLinks", pageLinks);
			model.addAttribute("curPage", curPage);// curPage�� null ���̸� 1�� ������ �ϹǷ�

			return "/bbs/rev_bbs";
		}

		// �Խ��� �۾���
		@RequestMapping(value = "/rev_bbs_write", method = RequestMethod.GET)
		public String write(String bbscd, Model model) throws Exception {

			// �Խ��� �̸�
			String bbsnm = bbsService.getBbsNm(bbscd);
			model.addAttribute("bbsnm", bbsnm);

			return "/bbs/rev_bbs_write";
		}

		// notice �Խ��� ���ۼ�
		@RequestMapping(value = "/rev_bbs_write", method = RequestMethod.POST)
		public String write(BbsVo bbsVo, MultipartHttpServletRequest mpRequest) throws Exception {
			bbsVo.setMemId("${memId}");
			System.out.println(bbsVo);
			bbsService.insert(bbsVo);
			bbsVo.setBbseditno(bbsService.getNewBbsVo().getBbseditno());

			// ���Ͼ��ε�
			List<MultipartFile> fileList = mpRequest.getFiles("upload");
			for (MultipartFile mf : fileList) {
				String filename = mf.getOriginalFilename();
				mf.transferTo(new File(WebContants.BASE_PATH + filename));
			}

			// ���ϵ����� ����
			int size = fileList.size();
			for (int i = 0; i < size; i++) {
				MultipartFile mpFile = fileList.get(i);
				AttachFile attachFile = new AttachFile();
				String filename = mpFile.getOriginalFilename();
				attachFile.setFilename(filename);
				attachFile.setFiletype(mpFile.getContentType());
				attachFile.setFilesize(mpFile.getSize());
				attachFile.setBbseditno(bbsVo.getBbseditno());
				bbsService.insertAttachFile(attachFile);
			}

			return "redirect:/rev_bbs?bbscd=" + bbsVo.getBbscd();
		}

		@RequestMapping(value = "/rev_bbs_detail", method = RequestMethod.GET)
		public String view(int bbseditno, String bbscd, Integer curPage, String searchWord, Model model) throws Exception {
			
			File dir = new File("C:\\spring\\spring2018\\project\\src\\main\\webapp\\resources\\imgs2"); 

			File[] fileList = dir.listFiles();

				System.out.println("start");
				for(int i = 0 ; i < fileList.length ; i++){

					File file = fileList[i]; 

					if(file.isFile()){

						System.out.println("\t ���� �̸� = " + file.getName());

					} else {
						System.out.println("���Ͼ���");
					}

				}

			System.out.println("end");

			int numPerPage = 10;// �������� ���ڵ� �� ����
			int pagePerBlock = 10;// ������ ��ũ�� �׷�(block)�� ũ�� ����
			if (searchWord == null)
				searchWord = ""; // �˻�� null �̸� ""���� ����
			// ��Ϻ���
			int totalRecord = bbsService.getTotalRecord(bbscd, searchWord);
			System.out.println(curPage);
			PagingHelper pagingHelper = new PagingHelper(totalRecord, curPage, numPerPage, pagePerBlock);
			bbsService.setPagingHelper(pagingHelper);

			int start = pagingHelper.getStartRecord();
			int end = pagingHelper.getEndRecord();

			// �󼼺���
			BbsVo thisBbsVo = bbsService.getBbsVo(bbseditno);
			BbsVo prevBbsVo = bbsService.getPrevBbsVo(bbseditno, bbscd, searchWord);
			BbsVo nextBbsVo = bbsService.getNextBbsVo(bbseditno, bbscd, searchWord);
			ArrayList<AttachFile> attachFileList = bbsService.getAttachFileList(bbseditno);
			ArrayList<Comment> commentList = bbsService.getCommentList(bbseditno);

			ArrayList<BbsVo> list = bbsService.getbbseditList(bbscd, searchWord, start, end);
			String bbsnm = bbsService.getBbsNm(bbscd);
			Integer no = bbsService.getListNo();
			Integer prevLink = bbsService.getPrevLink();
			Integer nextLink = bbsService.getNextLink();
			Integer firstPage = bbsService.getFirstPage();
			Integer lastPage = bbsService.getLastPage();
			int[] pageLinks = bbsService.getPageLinks();

			model.addAttribute("thisBbsVo", thisBbsVo);
			model.addAttribute("prevBbsVo", prevBbsVo);
			model.addAttribute("nextBbsVo", nextBbsVo);
			model.addAttribute("attachFileList", attachFileList);
			model.addAttribute("commentList", commentList);

			System.out.println(thisBbsVo);

			model.addAttribute("list", list);
			model.addAttribute("bbsnm", bbsnm);
			model.addAttribute("bbscd", bbscd);

			model.addAttribute("no", no);
			model.addAttribute("prevLink", prevLink);
			model.addAttribute("nextLink", nextLink);
			model.addAttribute("firstPage", firstPage);
			model.addAttribute("lastPage", lastPage);
			model.addAttribute("pageLinks", pageLinks);

			// ��ȸ�� ����
			bbsService.increaseHit(bbseditno);

			return "/bbs/rev_bbs_detail";
		}
		
		
		@RequestMapping(value = "/rev_bbs_detail/commentAdd", method = RequestMethod.POST)
		public String commentAdd(Integer bbseditno, String bbscd, Integer curPage, String searchWord, String memo)
				throws Exception {

			Comment comment = new Comment();
			comment.setMemo(memo);
			comment.setMemId("��ȸ��"); // �ӽ�
			comment.setBbseditno(bbseditno);
			bbsService.insertComment(comment);

			return "redirect:/rev_bbs_detail?bbseditno=" + bbseditno + "&bbscd=" + bbscd + "&curPage=" + curPage
					+ "&searchWord=" + searchWord;

		}

		@RequestMapping(value = "/rev_bbs_detail/commentUpdate", method = RequestMethod.POST)
		public String commentUpdate(Integer commentno, Integer bbseditno, String bbscd, Integer curPage, String searchWord,
				String memo) throws Exception {

			Comment comment = bbsService.getComment(commentno);
			comment.setMemo(memo);
			bbsService.updateComment(comment);
			// searchWord = URLEncoder.encode(searchWord, "UTF-8");

			return "redirect:/rev_bbs_detail?bbseditno=" + bbseditno + "&bbscd=" + bbscd + "&curPage=" + curPage
					+ "&searchWord=" + searchWord;

		}

		@RequestMapping(value = "/rev_bbs_detail/commentDel", method = RequestMethod.POST)
		public String commentDel(Integer commentno, Integer bbseditno, String bbscd, Integer curPage, String searchWord)
				throws Exception {

			bbsService.deleteComment(commentno);

			// searchWord = URLEncoder.encode(searchWord,"UTF-8");

			return "redirect:/rev_bbs_detail?bbseditno=" + bbseditno + "&bbscd=" + bbscd + "&curPage=" + curPage
					+ "&searchWord=" + searchWord;

		}
		
		
		

		// �Խñ� ����
		@RequestMapping(value = "/rev_bbs_delete", method = RequestMethod.GET)
		public String delete(int bbseditno, String searchWord, String bbscd) throws Exception {

			bbsService.delete(bbseditno);

			return "redirect:/rev_bbs?bbscd=" + bbscd + "&searchWord=" + searchWord;

		}

		// �Խñ� ����
		@RequestMapping(value = "/rev_bbs_edit", method = RequestMethod.GET)
		public String update(int bbseditno, String bbscd, Model model) throws Exception {

			BbsVo thisBbsVo = bbsService.getBbsVo(bbseditno);
			String bbsnm = bbsService.getBbsNm(bbscd);

			// �������������� ���� �Խñ� ����
			model.addAttribute("thisBbsVo", thisBbsVo);
			model.addAttribute("bbsnm", bbsnm);

			return "/bbs/rev_bbs_edit";
		}

		@RequestMapping(value = "/rev_bbs_edit", method = RequestMethod.POST)
		public String update(BbsVo bbsVo, Integer curPage, String bbscd, String searchWord, Model model,
				MultipartHttpServletRequest mpRequest) throws Exception {

			bbsService.update(bbsVo);

			// ���Ͼ��ε�
			List<MultipartFile> fileList = mpRequest.getFiles("upload");
			for (MultipartFile mf : fileList) {
				String filename = mf.getOriginalFilename();
				mf.transferTo(new File(WebContants.BASE_PATH + filename));
			}

			// ���ϵ����� ����
			int size = fileList.size();
			for (int i = 0; i < size; i++) {
				MultipartFile mpFile = fileList.get(i);
				AttachFile attachFile = new AttachFile();
				String filename = mpFile.getOriginalFilename();
				attachFile.setFilename(filename);
				attachFile.setFiletype(mpFile.getContentType());
				attachFile.setFilesize(mpFile.getSize());
				attachFile.setBbseditno(bbsVo.getBbseditno());
				bbsService.insertAttachFile(attachFile);
			}

			return "redirect:/rev_bbs_detail?bbseditno=" + bbsVo.getBbseditno() + "&bbscd=" + bbsVo.getBbscd() + "&curPage="
					+ curPage + "&searchWord=" + searchWord;
		}

}
