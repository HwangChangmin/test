package com.dycgv.service;

import com.dycgv.dao.LoginDAO;
import com.dycgv.vo.LoginVO;
import com.dycgv.vo.SessionVO;

public class LoginCheckService {

	/* 로그인 처리 메소드 */
	public SessionVO getLoginResult(LoginVO vo) {		
		LoginDAO dao = new LoginDAO();
		SessionVO svo = dao.getResultLogin(vo);

		return svo;
	}
}
