package kr.ac.sungkyul.mysite.web.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.sungkyul.mysite.dao.BoardDao;
import kr.ac.sungkyul.mysite.vo.BoardVo;
import kr.ac.sungkyul.web.Action;
import kr.ac.sungkyul.web.WebUtil;

public class BoardViewAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sno = request.getParameter("no");
		
		// 비어 있거나 숫자가 아닌 번호인 경우
		if( sno == null || sno.matches("-?\\d+(\\.\\d+)?") == false ){
			WebUtil.redirect("/mysite/board", request, response);
			return;
		}
		
		Long no = Long.parseLong(sno);
		
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
		BoardVo board = dao.getBoard(no);
		
		// 게시물이 존재하지 않는 경우(url을 통한 비정상 접근)
		if(board.getNo() == null){
			WebUtil.redirect("/mysite/board", request, response);
			return;
		}
		
		dao.updateViewCount(no);
		
		request.setAttribute("board", board);
		request.setAttribute("page", page);
		request.setAttribute("kwd", kwd);
		request.setAttribute("no", no);
		
		WebUtil.forward("/WEB-INF/views/board/view.jsp", request, response);
	}

}
