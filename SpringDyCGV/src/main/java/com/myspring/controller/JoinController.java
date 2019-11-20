package com.myspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dycgv.dao.JoinDAO;
import com.dycgv.service.JoinCheckService;
import com.dycgv.vo.JoinVO;

@Controller
public class JoinController {

	@RequestMapping(value = "/join.do", method = RequestMethod.GET)
	public String join() {
		return "/join/join";
	}

	// 회원가입
	@RequestMapping(value = "/join_proc.do", method = RequestMethod.POST)
	public String join_proc(JoinVO vo) {
		String resPage = "";
		boolean joinResult = false;

		// DB연동
		JoinCheckService service = new JoinCheckService();
		joinResult = service.getJoinResult(vo);

		if (joinResult) {
			resPage = "/join/join_success";
		} else {
			resPage = "/join/join_fail";
		}

		return resPage;
	}

	// 아이디 중복 확인(id_check_process.jsp파일은 필요없어짐)
	@RequestMapping(value = "/id_check_process.do", method = RequestMethod.GET)
	@ResponseBody
	public String id_check_process(String id) {
		JoinDAO dao = new JoinDAO();
		int result = dao.getResultCheckId(id);

		return String.valueOf(result);
	}
}
