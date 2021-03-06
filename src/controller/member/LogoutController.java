package controller.member;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.ModelAndView;
import model.dao.member.MemberDAO;
import model.vo.member.Member;

public class LogoutController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Member member = (Member)request.getSession().getAttribute("memberVO");
		String name = member.getName();

		request.getSession().invalidate();
		
		return new ModelAndView("/login.jsp?name="+name);
	}

}
