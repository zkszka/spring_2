package com.mysite.member1;

import com.mysite.member1.repository.MemberDao;
import com.mysite.member1.service.ChangePasswordService;
import com.mysite.member1.service.MemberRegisterService;

public class Factory {// 싱글톤 패턴
	private Factory() {}
	private static Factory instance = new Factory();
	public static Factory newInstance() {
		return instance;
	}
	
	// 필요한 데이터 객체를 생성해서 전달한다. @Configuration과 같음
	private MemberRegisterService regSvc = new MemberRegisterService();
	private ChangePasswordService changePwdSvc = new ChangePasswordService();
	private MemberDao memberDao = new MemberDao();
	
	public MemberRegisterService getMemberRegisterService() {
		return regSvc;
	}
	
	public ChangePasswordService getChangePasswordService() {
		return changePwdSvc;
	}
	
	public MemberDao getMemberDao() {
		return memberDao;
	}
}
