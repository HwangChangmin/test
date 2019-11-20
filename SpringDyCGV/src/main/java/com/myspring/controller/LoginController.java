package com.myspring.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dycgv.service.LoginCheckService;
import com.dycgv.vo.LoginVO;
import com.dycgv.vo.SessionVO;

@Controller
public class LoginController {

	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String login() {
		return "/login/login";
	}

	// �α��� ����, ����
	@RequestMapping(value = "/login_proc.do", method = RequestMethod.POST)
	public String login_proc(LoginVO vo, HttpSession session) {
		String resPage = "";
		LoginCheckService service = new LoginCheckService();
		SessionVO svo = service.getLoginResult(vo);
		
		// DB����

		if (svo.isResult()) {
			// ���ǵ�� * session�� ����ϴ� ��쿡�� ModelAndView�� ������� �ʾƵ� �ȴ�.
			session.setAttribute("svo", svo);
			session.setAttribute("sid", vo.getId());
			/*resPage = "/login/login_success";*/
			resPage = "/index";
		} else {
			System.out.println("name : ����");
			resPage = "/login/login_fail";
		}
		return resPage;
	}

	// �α׾ƿ�
	@RequestMapping(value = "/logout.do", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		// ���� ����
		SessionVO svo = (SessionVO) session.getAttribute("svo");
		if (svo != null) {
			session.invalidate();
		}
		return "/index";
	}

	/*
	 * @RequestMapping(value = "/login_proc2.do", method = RequestMethod.GET)
	 * //GET����̸� �ٷ� ���� �� �ִ�. public ModelAndView login_proc2() { ModelAndView mv =
	 * new ModelAndView(); LoginVO vo=new LoginVO(); vo.setId("honghong");
	 * vo.setPass("1234");
	 * 
	 * 
	 * mv.addObject("name", "ȫ�浿"); // ?name=ȫ�浿 �� ���� mv.addObject("vo", vo);
	 * mv.setViewName("/login/login_success");
	 * 
	 * return mv; }
	 */
}
