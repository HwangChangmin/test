package com.dycgv.service;

import com.dycgv.dao.LoginDAO;
import com.dycgv.vo.LoginVO;
import com.dycgv.vo.SessionVO;

public class LoginCheckService {

	/* �α��� ó�� �޼ҵ� */
	public SessionVO getLoginResult(LoginVO vo) {		
		LoginDAO dao = new LoginDAO();
		SessionVO svo = dao.getResultLogin(vo);

		return svo;
	}
}
