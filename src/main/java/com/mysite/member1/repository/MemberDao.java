package com.mysite.member1.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.mysite.member1.model.Member;

public class MemberDao {
	private static Map<String, Member> mapDB = new HashMap<String, Member>();
	// 회원 고유 번호 nextId
	private static long nextId = 0;
	
	public void insert(Member member) {
		// map은 put으로 키와 값 저장
		// 이메일 주소가 키가 된다
		member.setId(++nextId); // id는 생성자가 아닌 setter로 값을 넣는다.
		mapDB.put(member.getEmail(), member);
		System.out.println("mapDB : " + mapDB);
	}
	
	// 같은 이메일 주소가 있는지 확인하는 메서드 : DB연동이 필요하므로 service가 아닌 DAO에서 처리
	public Member selectByEmail(String email) {
		// 리턴값이 null이면 이메일 주소가 없다는 의미이다!
		return mapDB.get(email); // 키로 값을 검색
		
	}
	
	// 전체 회원정보 조회
	public Collection<Member> selectAll(){
		return mapDB.values(); // map에 있는 모든 정보 리턴
		// values의 리턴 타입이 Collection이다
	}
	
	// 패스워드 수정
	public void update(Member member) {// 수정할 암호를 담은 Member 객체를 얻어 이메일을 키로 값(객체)를 담는다
		mapDB.put(member.getEmail(), member);
		System.out.println("mapDB 수정: " + mapDB);
	}
	
	
	
}
