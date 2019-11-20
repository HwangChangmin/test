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

	// 로그인 성공, 실패
	@RequestMapping(value = "/login_proc.do", method = RequestMethod.POST)
	public String login_proc(LoginVO vo, HttpSession session) {
		String resPage = "";
		LoginCheckService service = new LoginCheckService();
		SessionVO svo = service.getLoginResult(vo);
		
		// DB연동

		if (svo.isResult()) {
			// 세션등록 * session을 사용하는 경우에는 ModelAndView를 사용하지 않아도 된다.
			session.setAttribute("svo", svo);
			session.setAttribute("sid", vo.getId());
			/*resPage = "/login/login_success";*/
			resPage = "/index";
		} else {
			System.out.println("name : 실패");
			resPage = "/login/login_fail";
		}
		return resPage;
	}

	// 로그아웃
	@RequestMapping(value = "/logout.do", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		// 세션 종료
		SessionVO svo = (SessionVO) session.getAttribute("svo");
		if (svo != null) {
			session.invalidate();
		}
		return "/index";
	}

	/*
	 * @RequestMapping(value = "/login_proc2.do", method = RequestMethod.GET)
	 * //GET방식이면 바로 받을 수 있다. public ModelAndView login_proc2() { ModelAndView mv =
	 * new ModelAndView(); LoginVO vo=new LoginVO(); vo.setId("honghong");
	 * vo.setPass("1234");
	 * 
	 * 
	 * mv.addObject("name", "홍길동"); // ?name=홍길동 과 같다 mv.addObject("vo", vo);
	 * mv.setViewName("/login/login_success");
	 * 
	 * return mv; }
	 */
}
