package kr.ac.sungkyul.mysite.web.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.sungkyul.mysite.dao.GuestBookDao;
import kr.ac.sungkyul.mysite.vo.GuestBookVo;
import kr.ac.sungkyul.web.Action;
import kr.ac.sungkyul.web.WebUtil;

public class GuestBookInsertAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String content = request.getParameter("content");
		
		GuestBookVo vo = new GuestBookVo();
		vo.setName(name);
		vo.setPassword(password);
		vo.setContent(content);
		
		GuestBookDao dao = new GuestBookDao();
		dao.insert(vo);
		
		WebUtil.redirect("/mysite/gb", request, response);
	}

}
