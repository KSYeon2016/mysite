package kr.ac.sungkyul.mysite.web.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.sungkyul.mysite.dao.UserDao;
import kr.ac.sungkyul.mysite.vo.UserVo;
import kr.ac.sungkyul.web.Action;
import kr.ac.sungkyul.web.WebUtil;

public class ModifyFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		// 인증 여부 확인
		if(session == null){
			WebUtil.redirect("/mysite/main", request, response);
			return;
		}
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null){
			WebUtil.redirect("/mysite/main", request, response);
			return;
		}
		
		// 인증이 되어 있음, 사용자 번호를 가지고 UserVo를 받아옴
		Long no = authUser.getNo();
		UserDao dao = new UserDao();
		
		UserVo vo = dao.get(no);
		
		request.setAttribute("userVo", vo);
		
		WebUtil.forward("/WEB-INF/views/user/modifyform.jsp", request, response);
	}

}
