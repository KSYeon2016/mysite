package kr.ac.sungkyul.mysite.web.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.sungkyul.mysite.dao.BoardDao;
import kr.ac.sungkyul.mysite.vo.BoardVo;
import kr.ac.sungkyul.web.Action;
import kr.ac.sungkyul.web.WebUtil;

public class BoardModifyFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long no = Long.parseLong(request.getParameter("no"));
		
		Integer page = 1;
		try {
			page = Integer.parseInt(request.getParameter("page"));
		} catch( NumberFormatException e ) {
			page = 1;
		}
		
		BoardDao dao = new BoardDao();
		BoardVo board = new BoardVo();
		board = dao.getBoard(no);
		
		request.setAttribute("board", board);
		request.setAttribute("page", page);
		
		WebUtil.forward("/WEB-INF/views/board/modify.jsp", request, response);
	}

}
