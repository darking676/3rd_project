package com.bit.shop01.controller.bbs;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

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

	// review 게시판 맵핑
	@RequestMapping(value = "/rev_bbs", method = { RequestMethod.GET, RequestMethod.POST })
	public String bbs(String bbscd, Integer curPage, String searchWord, Model model) throws Exception {

		if (bbscd == null)
			bbscd = "review";
		if (curPage == null)
			curPage = 1;
		if (searchWord == null)
			searchWord = "";

		int numPerPage = 10;// 페이지당 레코드 수 지정
		int pagePerBlock = 100;// 페이지 링크의 그룹(block)의 크기 지정

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
		model.addAttribute("curPage", curPage);// curPage는 null 값이면 1로 만들어야 하므로

		return "/bbs/rev_bbs";
	}

	// 게시판 글쓰기
	@RequestMapping(value = "/rev_bbs_write", method = RequestMethod.GET)
	public String write(String bbscd, Model model) throws Exception {

		// 게시판 이름
		String bbsnm = bbsService.getBbsNm(bbscd);
		model.addAttribute("bbsnm", bbsnm);

		return "/bbs/rev_bbs_write";
	}

	// notice 게시판 글작성
	@RequestMapping(value = "/rev_bbs_write", method = RequestMethod.POST)
	public String write(BbsVo bbsVo, MultipartHttpServletRequest mpRequest) throws Exception {
		bbsVo.setMemId("${memId}");
		System.out.println(bbsVo);
		bbsService.insert(bbsVo);
		bbsVo.setBbseditno(bbsService.getNewBbsVo().getBbseditno());

		// 파일업로드
		List<MultipartFile> fileList = mpRequest.getFiles("upload");
		for (MultipartFile mf : fileList) {
			String filename = mf.getOriginalFilename();
			if(!filename.equals("")) {
			mf.transferTo(new File(WebContants.BASE_PATH + filename));
			}
		}

		// 파일데이터 삽입
		int size = fileList.size();
		for (int i = 0; i < size; i++) {
			MultipartFile mpFile = fileList.get(i);
			AttachFile attachFile = new AttachFile();
			String filename = mpFile.getOriginalFilename();
			if(!filename.equals("")) {
			attachFile.setFilename(filename);
			attachFile.setFiletype(mpFile.getContentType());
			attachFile.setFilesize(mpFile.getSize());
			attachFile.setBbseditno(bbsVo.getBbseditno());
			bbsService.insertAttachFile(attachFile);
			}
		}
		return "redirect:/rev_bbs/?bbscd=" + bbsVo.getBbscd();
	}

	@RequestMapping(value = "/rev_bbs_detail", method = RequestMethod.GET)
	public String view(int bbseditno, String bbscd, Integer curPage, String searchWord, Model model) throws Exception {
		
		File dir = new File("C:\\spring\\spring2018\\project\\src\\main\\webapp\\resources\\imgs2"); 

		File[] fileList = dir.listFiles();

			System.out.println("start");
			for(int i = 0 ; i < fileList.length ; i++){

				File file = fileList[i]; 

				if(file.isFile()){

					System.out.println("\t 파일 이름 = " + file.getName());

				} else {
					System.out.println("파일없음");
				}

			}

		System.out.println("end");

		int numPerPage = 10;// 페이지당 레코드 수 지정
		int pagePerBlock = 10;// 페이지 링크의 그룹(block)의 크기 지정
		if (searchWord == null)
			searchWord = ""; // 검색어가 null 이면 ""으로 변경
		// 목록보기
		int totalRecord = bbsService.getTotalRecord(bbscd, searchWord);
		System.out.println(curPage);
		PagingHelper pagingHelper = new PagingHelper(totalRecord, curPage, numPerPage, pagePerBlock);
		bbsService.setPagingHelper(pagingHelper);

		int start = pagingHelper.getStartRecord();
		int end = pagingHelper.getEndRecord();

		// 상세보기
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

		// 조회수 증가
		bbsService.increaseHit(bbseditno);

		return "/bbs/rev_bbs_detail";
	}
	
	
	@RequestMapping(value = "/rev_bbs_detail/commentAdd", method = RequestMethod.POST)
	public String commentAdd(Integer bbseditno, String bbscd, Integer curPage, String searchWord, String memo)
			throws Exception {

		Comment comment = new Comment();
		comment.setMemo(memo);
//		comment.setMemId("비회원"); // 임시
		comment.setBbseditno(bbseditno);
		bbsService.insertComment(comment);

		return "redirect:/rev_bbs_detail/?bbseditno=" + bbseditno + "&bbscd=" + bbscd + "&curPage=" + curPage
				+ "&searchWord=" + searchWord;

	}

	@RequestMapping(value = "/rev_bbs_detail/commentUpdate", method = RequestMethod.POST)
	public String commentUpdate(Integer commentno, Integer bbseditno, String bbscd, Integer curPage, String searchWord,
			String memo) throws Exception {

		Comment comment = bbsService.getComment(commentno);
		comment.setMemo(memo);
		bbsService.updateComment(comment);
		// searchWord = URLEncoder.encode(searchWord, "UTF-8");

		return "redirect:/rev_bbs_detail/?bbseditno=" + bbseditno + "&bbscd=" + bbscd + "&curPage=" + curPage
				+ "&searchWord=" + searchWord;

	}

	@RequestMapping(value = "/rev_bbs_detail/commentDel", method = RequestMethod.POST)
	public String commentDel(Integer commentno, Integer bbseditno, String bbscd, Integer curPage, String searchWord)
			throws Exception {

		bbsService.deleteComment(commentno);

		// searchWord = URLEncoder.encode(searchWord,"UTF-8");

		return "redirect:/rev_bbs_detail/?bbseditno=" + bbseditno + "&bbscd=" + bbscd + "&curPage=" + curPage
				+ "&searchWord=" + searchWord;

	}
	
	
	

	// 게시글 삭제
	@RequestMapping(value = "/rev_bbs_delete", method = RequestMethod.GET)
	public String delete(int bbseditno, String searchWord, String bbscd) throws Exception {

		bbsService.delete(bbseditno);

		return "redirect:/rev_bbs/?bbscd=" + bbscd + "&searchWord=" + searchWord;

	}

	// 게시글 수정
	@RequestMapping(value = "/rev_bbs_edit", method = RequestMethod.GET)
	public String update(int bbseditno, String bbscd, Model model) throws Exception {

		BbsVo thisBbsVo = bbsService.getBbsVo(bbseditno);
		String bbsnm = bbsService.getBbsNm(bbscd);

		// 수정페이지에서 보일 게시글 정보
		model.addAttribute("thisBbsVo", thisBbsVo);
		model.addAttribute("bbsnm", bbsnm);

		return "/bbs/rev_bbs_edit";
	}

	@RequestMapping(value = "/rev_bbs_edit", method = RequestMethod.POST)
	public String update(BbsVo bbsVo, Integer curPage, String bbscd, String searchWord, Model model,
			MultipartHttpServletRequest mpRequest) throws Exception {

		bbsService.update(bbsVo);

		// 파일업로드
		List<MultipartFile> fileList = mpRequest.getFiles("upload");
		for (MultipartFile mf : fileList) {
			String filename = mf.getOriginalFilename();
			mf.transferTo(new File(WebContants.BASE_PATH + filename));
		}

		// 파일데이터 삽입
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

		return "redirect:/rev_bbs_detail/?bbseditno=" + bbsVo.getBbseditno() + "&bbscd=" + bbsVo.getBbscd() + "&curPage="
				+ curPage + "&searchWord=" + searchWord;
	}
	
	
	// 로그인 후 회원용 게시판
	// review 게시판 맵핑
		@RequestMapping(value = "/rev_bbs2", method = { RequestMethod.GET, RequestMethod.POST })
		public String bbs2(String bbscd, Integer curPage, String searchWord, Model model) throws Exception {

			if (bbscd == null)
				bbscd = "review";
			if (curPage == null)
				curPage = 1;
			if (searchWord == null)
				searchWord = "";

			int numPerPage = 10;// 페이지당 레코드 수 지정
			int pagePerBlock = 100;// 페이지 링크의 그룹(block)의 크기 지정

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
			model.addAttribute("curPage", curPage);// curPage는 null 값이면 1로 만들어야 하므로

			return "/bbs2/rev_bbs2";
		}

		// 게시판 글쓰기
		@RequestMapping(value = "/rev_bbs_write2", method = RequestMethod.GET)
		public String write2(String bbscd, Model model) throws Exception {

			// 게시판 이름
			String bbsnm = bbsService.getBbsNm(bbscd);
			model.addAttribute("bbsnm", bbsnm);

			return "/bbs2/rev_bbs_write2";
		}

		// review 게시판 글작성
		@RequestMapping(value = "/rev_bbs_write2", method = RequestMethod.POST)
		public String write2(BbsVo bbsVo, MultipartHttpServletRequest mpRequest) throws Exception {
//			bbsVo.setMemId("${memId}");
			System.out.println(bbsVo);
			bbsService.insert(bbsVo);
			bbsVo.setBbseditno(bbsService.getNewBbsVo().getBbseditno());

			// 파일업로드
			List<MultipartFile> fileList = mpRequest.getFiles("upload");
			for (MultipartFile mf : fileList) {
				String filename = mf.getOriginalFilename();
				if(!filename.equals("")) {
				mf.transferTo(new File(WebContants.BASE_PATH + filename));
				}
			}

			// 파일데이터 삽입
			int size = fileList.size();
			for (int i = 0; i < size; i++) {
				MultipartFile mpFile = fileList.get(i);
				AttachFile attachFile = new AttachFile();
				String filename = mpFile.getOriginalFilename();
				if(!filename.equals("")) {
				attachFile.setFilename(filename);
				attachFile.setFiletype(mpFile.getContentType());
				attachFile.setFilesize(mpFile.getSize());
				attachFile.setBbseditno(bbsVo.getBbseditno());
				bbsService.insertAttachFile(attachFile);
				}
			}
			return "redirect:/rev_bbs2/?bbscd=" + bbsVo.getBbscd();
		}

		@RequestMapping(value = "/rev_bbs_detail2", method = RequestMethod.GET)
		public String view2(int bbseditno, String bbscd, Integer curPage, String searchWord, Model model) throws Exception {
			
			File dir = new File("C:\\spring\\spring2018\\project\\src\\main\\webapp\\resources\\imgs2"); 

			File[] fileList = dir.listFiles();

				System.out.println("start");
				for(int i = 0 ; i < fileList.length ; i++){

					File file = fileList[i]; 

					if(file.isFile()){

						System.out.println("\t 파일 이름 = " + file.getName());

					} else {
						System.out.println("파일없음");
					}

				}

			System.out.println("end");

			int numPerPage = 10;// 페이지당 레코드 수 지정
			int pagePerBlock = 10;// 페이지 링크의 그룹(block)의 크기 지정
			if (searchWord == null)
				searchWord = ""; // 검색어가 null 이면 ""으로 변경
			// 목록보기
			int totalRecord = bbsService.getTotalRecord(bbscd, searchWord);
			System.out.println(curPage);
			PagingHelper pagingHelper = new PagingHelper(totalRecord, curPage, numPerPage, pagePerBlock);
			bbsService.setPagingHelper(pagingHelper);

			int start = pagingHelper.getStartRecord();
			int end = pagingHelper.getEndRecord();

			// 상세보기
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

			// 조회수 증가
			bbsService.increaseHit(bbseditno);

			return "/bbs2/rev_bbs_detail2";
		}
		
		
		@RequestMapping(value = "/rev_bbs_detail2/commentAdd", method = RequestMethod.POST)
		public String commentAdd2(Integer bbseditno, String bbscd, Integer curPage, String searchWord, String memo)
				throws Exception {

			Comment comment = new Comment();
			comment.setMemo(memo);
//			comment.setMemId("비회원"); // 임시
			comment.setBbseditno(bbseditno);
			bbsService.insertComment(comment);

			return "redirect:/rev_bbs_detail2/?bbseditno=" + bbseditno + "&bbscd=" + bbscd + "&curPage=" + curPage
					+ "&searchWord=" + searchWord;

		}

		@RequestMapping(value = "/rev_bbs_detail2/commentUpdate", method = RequestMethod.POST)
		public String commentUpdate2(Integer commentno, Integer bbseditno, String bbscd, Integer curPage, String searchWord,
				String memo) throws Exception {

			Comment comment = bbsService.getComment(commentno);
			comment.setMemo(memo);
			bbsService.updateComment(comment);
			// searchWord = URLEncoder.encode(searchWord, "UTF-8");

			return "redirect:/rev_bbs_detail2/?bbseditno=" + bbseditno + "&bbscd=" + bbscd + "&curPage=" + curPage
					+ "&searchWord=" + searchWord;

		}

		@RequestMapping(value = "/rev_bbs_detail2/commentDel", method = RequestMethod.POST)
		public String commentDel2(Integer commentno, Integer bbseditno, String bbscd, Integer curPage, String searchWord)
				throws Exception {

			bbsService.deleteComment(commentno);

			// searchWord = URLEncoder.encode(searchWord,"UTF-8");

			return "redirect:/rev_bbs_detail2/?bbseditno=" + bbseditno + "&bbscd=" + bbscd + "&curPage=" + curPage
					+ "&searchWord=" + searchWord;

		}
		
		
		

		// 게시글 삭제
		@RequestMapping(value = "/rev_bbs_delete2", method = RequestMethod.GET)
		public String delete2(int bbseditno, String searchWord, String bbscd) throws Exception {

			bbsService.delete(bbseditno);

			return "redirect:/rev_bbs2/?bbscd=" + bbscd + "&searchWord=" + searchWord;

		}

		// 게시글 수정
		@RequestMapping(value = "/rev_bbs_edit2", method = RequestMethod.GET)
		public String update2(int bbseditno, String bbscd, Model model) throws Exception {

			BbsVo thisBbsVo = bbsService.getBbsVo(bbseditno);
			String bbsnm = bbsService.getBbsNm(bbscd);

			// 수정페이지에서 보일 게시글 정보
			model.addAttribute("thisBbsVo", thisBbsVo);
			model.addAttribute("bbsnm", bbsnm);

			return "/bbs2/rev_bbs_edit2";
		}

		@RequestMapping(value = "/rev_bbs_edit2", method = RequestMethod.POST)
		public String update2(BbsVo bbsVo, Integer curPage, String bbscd, String searchWord, Model model,
				MultipartHttpServletRequest mpRequest) throws Exception {

			bbsService.update(bbsVo);

			// 파일업로드
			List<MultipartFile> fileList = mpRequest.getFiles("upload");
			for (MultipartFile mf : fileList) {
				String filename = mf.getOriginalFilename();
				mf.transferTo(new File(WebContants.BASE_PATH + filename));
			}

			// 파일데이터 삽입
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

			return "redirect:/rev_bbs_detail2/?bbseditno=" + bbsVo.getBbseditno() + "&bbscd=" + bbsVo.getBbscd() + "&curPage="
					+ curPage + "&searchWord=" + searchWord;
		}

	
		
		
	
		// 관리자 로그인 후 
		
		
		
		@RequestMapping(value = "/rev_bbs3", method = { RequestMethod.GET, RequestMethod.POST })
		public String bbs3(String bbscd, Integer curPage, String searchWord, Model model) throws Exception {

			if (bbscd == null)
				bbscd = "review";
			if (curPage == null)
				curPage = 1;
			if (searchWord == null)
				searchWord = "";

			int numPerPage = 10;// 페이지당 레코드 수 지정
			int pagePerBlock = 100;// 페이지 링크의 그룹(block)의 크기 지정

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
			model.addAttribute("curPage", curPage);// curPage는 null 값이면 1로 만들어야 하므로

			return "/bbs3/rev_bbs3";
		}

		// 게시판 글쓰기
		@RequestMapping(value = "/rev_bbs_write3", method = RequestMethod.GET)
		public String write3(String bbscd, Model model) throws Exception {

			// 게시판 이름
			String bbsnm = bbsService.getBbsNm(bbscd);
			model.addAttribute("bbsnm", bbsnm);

			return "/bbs3/rev_bbs_write3";
		}

		// review 게시판 글작성
		@RequestMapping(value = "/rev_bbs_write3", method = RequestMethod.POST)
		public String write3(BbsVo bbsVo, MultipartHttpServletRequest mpRequest) throws Exception {
//			bbsVo.setMemId("${memId}");
			System.out.println(bbsVo);
			bbsService.insert(bbsVo);
			bbsVo.setBbseditno(bbsService.getNewBbsVo().getBbseditno());

			// 파일업로드
			List<MultipartFile> fileList = mpRequest.getFiles("upload");
			for (MultipartFile mf : fileList) {
				String filename = mf.getOriginalFilename();
				if(!filename.equals("")) {
				mf.transferTo(new File(WebContants.BASE_PATH + filename));
				}
			}

			// 파일데이터 삽입
			int size = fileList.size();
			for (int i = 0; i < size; i++) {
				MultipartFile mpFile = fileList.get(i);
				AttachFile attachFile = new AttachFile();
				String filename = mpFile.getOriginalFilename();
				if(!filename.equals("")) {
				attachFile.setFilename(filename);
				attachFile.setFiletype(mpFile.getContentType());
				attachFile.setFilesize(mpFile.getSize());
				attachFile.setBbseditno(bbsVo.getBbseditno());
				bbsService.insertAttachFile(attachFile);
				}
			}
			return "redirect:/rev_bbs3/?bbscd=" + bbsVo.getBbscd();
		}

		@RequestMapping(value = "/rev_bbs_detail3", method = RequestMethod.GET)
		public String view3(int bbseditno, String bbscd, Integer curPage, String searchWord, Model model) throws Exception {
			
			File dir = new File("C:\\spring\\spring2018\\project\\src\\main\\webapp\\resources\\imgs2"); 

			File[] fileList = dir.listFiles();

				System.out.println("start");
				for(int i = 0 ; i < fileList.length ; i++){

					File file = fileList[i]; 

					if(file.isFile()){

						System.out.println("\t 파일 이름 = " + file.getName());

					} else {
						System.out.println("파일없음");
					}

				}

			System.out.println("end");

			int numPerPage = 10;// 페이지당 레코드 수 지정
			int pagePerBlock = 10;// 페이지 링크의 그룹(block)의 크기 지정
			if (searchWord == null)
				searchWord = ""; // 검색어가 null 이면 ""으로 변경
			// 목록보기
			int totalRecord = bbsService.getTotalRecord(bbscd, searchWord);
			System.out.println(curPage);
			PagingHelper pagingHelper = new PagingHelper(totalRecord, curPage, numPerPage, pagePerBlock);
			bbsService.setPagingHelper(pagingHelper);

			int start = pagingHelper.getStartRecord();
			int end = pagingHelper.getEndRecord();

			// 상세보기
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

			// 조회수 증가
			bbsService.increaseHit(bbseditno);

			return "/bbs3/rev_bbs_detail3";
		}
		
		
		@RequestMapping(value = "/rev_bbs_detail3/commentAdd", method = RequestMethod.POST)
		public String commentAdd3(Integer bbseditno, String bbscd, Integer curPage, String searchWord, String memo)
				throws Exception {

			Comment comment = new Comment();
			comment.setMemo(memo);
//			comment.setMemId("비회원"); // 임시
			comment.setBbseditno(bbseditno);
			bbsService.insertComment(comment);

			return "redirect:/rev_bbs_detail3/?bbseditno=" + bbseditno + "&bbscd=" + bbscd + "&curPage=" + curPage
					+ "&searchWord=" + searchWord;

		}

		@RequestMapping(value = "/rev_bbs_detail3/commentUpdate", method = RequestMethod.POST)
		public String commentUpdate3(Integer commentno, Integer bbseditno, String bbscd, Integer curPage, String searchWord,
				String memo) throws Exception {

			Comment comment = bbsService.getComment(commentno);
			comment.setMemo(memo);
			bbsService.updateComment(comment);
			// searchWord = URLEncoder.encode(searchWord, "UTF-8");

			return "redirect:/rev_bbs_detail2/?bbseditno=" + bbseditno + "&bbscd=" + bbscd + "&curPage=" + curPage
					+ "&searchWord=" + searchWord;

		}

		@RequestMapping(value = "/rev_bbs_detail3/commentDel", method = RequestMethod.POST)
		public String commentDel3(Integer commentno, Integer bbseditno, String bbscd, Integer curPage, String searchWord)
				throws Exception {

			bbsService.deleteComment(commentno);

			// searchWord = URLEncoder.encode(searchWord,"UTF-8");

			return "redirect:/rev_bbs_detail3/?bbseditno=" + bbseditno + "&bbscd=" + bbscd + "&curPage=" + curPage
					+ "&searchWord=" + searchWord;

		}
		
		
		

		// 게시글 삭제
		@RequestMapping(value = "/rev_bbs_delete3", method = RequestMethod.GET)
		public String delete3(int bbseditno, String searchWord, String bbscd) throws Exception {

			bbsService.delete(bbseditno);

			return "redirect:/rev_bbs3/?bbscd=" + bbscd + "&searchWord=" + searchWord;

		}

		// 게시글 수정
		@RequestMapping(value = "/rev_bbs_edit3", method = RequestMethod.GET)
		public String update3(int bbseditno, String bbscd, Model model) throws Exception {

			BbsVo thisBbsVo = bbsService.getBbsVo(bbseditno);
			String bbsnm = bbsService.getBbsNm(bbscd);

			// 수정페이지에서 보일 게시글 정보
			model.addAttribute("thisBbsVo", thisBbsVo);
			model.addAttribute("bbsnm", bbsnm);

			return "/bbs3/rev_bbs_edit3";
		}

		@RequestMapping(value = "/rev_bbs_edit3", method = RequestMethod.POST)
		public String update3(BbsVo bbsVo, Integer curPage, String bbscd, String searchWord, Model model,
				MultipartHttpServletRequest mpRequest) throws Exception {

			bbsService.update(bbsVo);

			// 파일업로드3
			
			List<MultipartFile> fileList = mpRequest.getFiles("upload");
			for (MultipartFile mf : fileList) {
				String filename = mf.getOriginalFilename();
				mf.transferTo(new File(WebContants.BASE_PATH + filename));
			}

			// 파일데이터 삽입
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

			return "redirect:/rev_bbs_detail3/?bbseditno=" + bbsVo.getBbseditno() + "&bbscd=" + bbsVo.getBbscd() + "&curPage="
					+ curPage + "&searchWord=" + searchWord;
		}

		
	

}
