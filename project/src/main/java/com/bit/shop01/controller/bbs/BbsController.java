package com.bit.shop01.controller.bbs;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bit.shop01.bbs.BbsService;
import com.bit.shop01.bbs.BbsVo;
import com.bit.shop01.page.PagingHelper;
import com.bit.shop01.bbs.Comment;

@Controller
public class BbsController {

	@Autowired
	private BbsService bbsService;

	@RequestMapping(value = "/not_bbs", method = { RequestMethod.GET, RequestMethod.POST })
	public String bbs(String bbscd, Integer curPage, String searchWord, Model model) throws Exception {

		if (bbscd == null)
			bbscd = "notice";
		if (curPage == null)
			curPage = 1;
		if (searchWord == null)
			searchWord = "";

		int numPerPage = 10;
		int pagePerBlock = 100;

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
		model.addAttribute("curPage", curPage);

		System.out.println(searchWord);
		return "/bbs/not_bbs";
	}

	@RequestMapping(value = "/not_bbs_write", method = RequestMethod.GET)
	public String write(String bbscd, Model model) throws Exception {

		String bbsnm = bbsService.getBbsNm(bbscd);
		model.addAttribute("bbsnm", bbsnm);

		return "/bbs/not_bbs_write";
	}

	@RequestMapping(value = "/not_bbs_write", method = RequestMethod.POST)
	public String write(BbsVo bbsVo) throws Exception {
		bbsVo.setMemId("${memId}");
		System.out.println(bbsVo);
		bbsService.insert(bbsVo);
		return "redirect:/not_bbs?bbscd=" + bbsVo.getBbscd();
	}

	@RequestMapping(value = "/not_bbs_detail", method = RequestMethod.GET)
	public String view(int bbseditno, String bbscd, Integer curPage, String searchWord, Model model) throws Exception {

		int numPerPage = 10;
		int pagePerBlock = 10;
		if (searchWord == null)
			searchWord = ""; 
		
		int totalRecord = bbsService.getTotalRecord(bbscd, searchWord);
		System.out.println(curPage);
		PagingHelper pagingHelper = new PagingHelper(totalRecord, curPage, numPerPage, pagePerBlock);
		bbsService.setPagingHelper(pagingHelper);

		int start = pagingHelper.getStartRecord();
		int end = pagingHelper.getEndRecord();

		BbsVo thisBbsVo = bbsService.getBbsVo(bbseditno);
		BbsVo prevBbsVo = bbsService.getPrevBbsVo(bbseditno, bbscd, searchWord);
		BbsVo nextBbsVo = bbsService.getNextBbsVo(bbseditno, bbscd, searchWord);
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

		bbsService.increaseHit(bbseditno);

		return "/bbs/not_bbs_detail";
	}

	@RequestMapping(value = "/not_bbs_detail/commentAdd", method = RequestMethod.POST)
	public String commentAdd(Integer bbseditno, String bbscd, Integer curPage, String searchWord, String memo)
			throws Exception {

		Comment comment = new Comment();
		comment.setMemo(memo);
		comment.setMemId("d"); 
		comment.setBbseditno(bbseditno);
		bbsService.insertComment(comment);

		return "redirect:/not_bbs_detail?bbseditno=" + bbseditno + "&bbscd=" + bbscd + "&curPage=" + curPage
				+ "&searchWord=" + searchWord;

	}

	@RequestMapping(value = "/not_bbs_detail/commentUpdate", method = RequestMethod.POST)
	public String commentUpdate(Integer commentno, Integer bbseditno, String bbscd, Integer curPage, String searchWord,
			String memo) throws Exception {

		Comment comment = bbsService.getComment(commentno);
		comment.setMemo(memo);
		bbsService.updateComment(comment);
		// searchWord = URLEncoder.encode(searchWord, "UTF-8");

		return "redirect:/not_bbs_detail?bbseditno=" + bbseditno + "&bbscd=" + bbscd + "&curPage=" + curPage
				+ "&searchWord=" + searchWord;

	}

	@RequestMapping(value = "/not_bbs_detail/commentDel", method = RequestMethod.POST)
	public String commentDel(Integer commentno, Integer bbseditno, String bbscd, Integer curPage, String searchWord)
			throws Exception {

		bbsService.deleteComment(commentno);

		// searchWord = URLEncoder.encode(searchWord,"UTF-8");

		return "redirect:/not_bbs_detail?bbseditno=" + bbseditno + "&bbscd=" + bbscd + "&curPage=" + curPage
				+ "&searchWord=" + searchWord;

	}

	@RequestMapping(value = "/not_bbs_delete", method = RequestMethod.GET)
	public String delete(int bbseditno, String searchWord, String bbscd) throws Exception {

		bbsService.delete(bbseditno);

		return "redirect:/not_bbs?bbscd=" + bbscd + "&searchWord=" + searchWord;

	}

	@RequestMapping(value = "/not_bbs_edit", method = RequestMethod.GET)
	public String update(int bbseditno, String bbscd, Model model) throws Exception {

		BbsVo thisBbsVo = bbsService.getBbsVo(bbseditno);
		String bbsnm = bbsService.getBbsNm(bbscd);

		model.addAttribute("thisBbsVo", thisBbsVo);
		model.addAttribute("bbsnm", bbsnm);

		return "/bbs/not_bbs_edit";
	}

	@RequestMapping(value = "/not_bbs_edit", method = RequestMethod.POST)
	public String update(BbsVo bbsVo, Integer curPage, String bbscd, String searchWord, Model model) throws Exception {

		bbsService.update(bbsVo);

		return "redirect:/not_bbs_detail?bbseditno=" + bbsVo.getBbseditno() + "&bbscd=" + bbsVo.getBbscd() + "&curPage="
				+ curPage + "&searchWord=" + searchWord;
	}

}
