package kr.ac.sungkyul.mysite.web.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.sungkyul.mysite.dao.BoardDao;
import kr.ac.sungkyul.web.Action;
import kr.ac.sungkyul.web.WebUtil;

public class BoardDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long no = Long.parseLong(request.getParameter("no"));
		Integer page = Integer.parseInt(request.getParameter("page"));
		String kwd = request.getParameter("kwd");
		
		if(kwd == null){
			kwd = "";
		}
		
		BoardDao dao = new BoardDao();
		dao.delete(no);
		
		WebUtil.redirect("/mysite/board?page=" + page + "&kwd=" + kwd, request, response);
		return;
	}

}
