package kr.ac.sungkyul.mysite.web.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.sungkyul.mysite.dao.BoardDao;
import kr.ac.sungkyul.mysite.vo.BoardVo;
import kr.ac.sungkyul.web.Action;
import kr.ac.sungkyul.web.WebUtil;

public class BoardListAction implements Action {
	private static final int ROW = 5;
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer page = 1;
		
		try {
			page = Integer.parseInt(request.getParameter("page"));
		} catch( NumberFormatException e ) {
			page = 1;
		}
		
		String kwd = request.getParameter("kwd");
		
		if(kwd == null){
			kwd = "";
		}
		
		BoardDao dao = new BoardDao();
		List<BoardVo> list = dao.getList(kwd);
		List<BoardVo> pageList = dao.getList(page, ROW, kwd);
		
		// countPage 계산
		Integer boardLength = list.size();	// 글의 수
		Integer i = boardLength % ROW;
		Integer countPage = 0;
		if(i == 0){
			countPage = boardLength/ROW;
		} else {
			countPage = boardLength/ROW + 1;
		}
		
		// minPage, maxPage
		Integer minPage = 0;
		Integer maxPage = 0;
		if(countPage > 5 && page > 3 && page < (countPage-2)){
			minPage = page -2;
			maxPage = page +2;
		} else if(countPage > 5 && page >= (countPage-2)){
			minPage = countPage-4;
			maxPage = countPage;
		} else if(countPage > 5 && page <= 3){
			minPage = 1;
			maxPage = 5;
		} else if(countPage <= 5){
			minPage = 1;
			maxPage = countPage;
		}
		
		request.setAttribute("pageList", pageList);
		request.setAttribute("page", page);
		request.setAttribute("row", ROW);
		request.setAttribute("countPage", countPage);
		request.setAttribute("minPage", minPage);
		request.setAttribute("maxPage", maxPage);
		request.setAttribute("kwd", kwd);
		
		WebUtil.forward("/WEB-INF/views/board/list.jsp", request, response);
	}

}