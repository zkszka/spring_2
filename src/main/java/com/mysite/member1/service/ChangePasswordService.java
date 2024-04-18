package com.mysite.member1.service;

import com.mysite.member1.Factory;
import com.mysite.member1.model.Member;
import com.mysite.member1.repository.MemberDao;

public class ChangePasswordService {
	private MemberDao memberDao = new MemberDao();
	//private Factory factory = Factory.newInstance();
	
	public void changePassword(String email, String oldPass, String newPass) {
		// 해당 이메일이 존재하는지 여부 검사
		Member member = memberDao.selectByEmail(email);
		
		// member값이 null이 아니라면 키에 값이(이메일 주소) 있다는 의미이다. 
		if(member == null) {
			System.out.println("해당 멤버가 없습니다.");
			return;
		}
		
		// 현재 비밀번호가 맞는지 검사 
		if(!member.getPassword().equals(oldPass)) {
			System.out.println("현재 비밀번호가 맞지 않습니다.");
			return;
		}
		
		member.setPassword(newPass);
		memberDao.update(member);
	}
}
