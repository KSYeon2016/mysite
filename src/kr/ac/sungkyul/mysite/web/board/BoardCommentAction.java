package kr.ac.sungkyul.mysite.web.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.sungkyul.mysite.dao.BoardDao;
import kr.ac.sungkyul.mysite.vo.BoardVo;
import kr.ac.sungkyul.mysite.vo.UserVo;
import kr.ac.sungkyul.web.Action;
import kr.ac.sungkyul.web.WebUtil;

public class BoardCommentAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		Integer page = Integer.parseInt(request.getParameter("page"));
		
		String kwd = request.getParameter("kwd");
		if(kwd == null){
			kwd = "";
		}
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		Long userNo = authUser.getNo();
		Long boardNo = Long.parseLong(request.getParameter("no"));
		
		BoardVo vo = new BoardVo();
		vo.setTitle(title);
		vo.setContent(content);
		vo.setUserNo(userNo);
		
		BoardDao dao = new BoardDao();
		dao.insertComment(vo, boardNo);
		
		WebUtil.redirect("/mysite/board?page=" + page + "&kwd=" + kwd, request, response);
	}

}
