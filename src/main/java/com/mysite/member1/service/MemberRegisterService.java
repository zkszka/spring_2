package com.mysite.member1.service;

import java.util.Collection;
import java.util.Date;

import com.mysite.member1.Factory;
import com.mysite.member1.model.Member;
import com.mysite.member1.model.RegisterRequest;
import com.mysite.member1.repository.MemberDao;

public class MemberRegisterService {
	// app으로부터 데이터를 받아 DAO로 값을 전달한다.
	private MemberDao memberDao = new MemberDao();
	//private Factory factory = Factory.newInstance();
	
	public void register(RegisterRequest req) {
		// DAO에는 멤버 객체를 전달하면 된다.
		// 같은 이메일 주소가 있는지 확인
		Member member = memberDao.selectByEmail(req.getEmail());
		//Member member = factory.getMemberDao().selectByEmail(req.getEmail());
		
		// member값이 null이 아니라면 키에 값이(이메일 주소) 있다는 의미이다. 
		if(member != null) {
			System.out.println("같은 이메일 주소가 있습니다.");
			return;
		}
		
		// 두 개의 패스워드가 맞는지 확인
		if(!req.getPassword().equals(req.getPasswordConfirm())) {
			System.out.println("패스워드를 잘 못 입력하였습니다.");
			return;
		}
		
		Member newMem = new Member(req.getName(), req.getEmail(), req.getPassword(), new Date());
		memberDao.insert(newMem);
	}
	
	// DAO에서 받은 값을 app(main) 페이지로 넘긴다
	public Collection<Member> selectAll(){
		return memberDao.selectAll();
	}
}
