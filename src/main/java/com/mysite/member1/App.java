package com.mysite.member1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;

import com.mysite.member1.model.Member;
import com.mysite.member1.model.RegisterRequest;
import com.mysite.member1.service.ChangePasswordService;
import com.mysite.member1.service.MemberRegisterService;

/**
 * 앞에 명령어를 통해 페이지 동작하는 프로그램
 * exit 종료
 * new 이메일 이름 암호 암호확인
 * change 이메일 현재암호 바꿀암호
 * list 모든 회원 조회
 * info 이메일을 입력하면 한 사람만 조회 가능
 */
public class App { 
	private Factory factory = Factory.newInstance();
       
	public static void main( String[] args ) throws IOException{
       // 키보드로부터 한 줄씩 입력받기
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       // App 클래스 내에 생성한 메서드를 호출하기 위한 객체 app
       App app = new App();
       
       while(true) {
    	   
    	   System.out.println("명령어를 입력하세요 : ");
			
    	   // Scanner는 공백을 인식하지 못한다
			//Scanner sc = new Scanner(System.in);

			String command = br.readLine();
			// String command = sc.next();

			// 입력한 문장에서 공백을 기준으로 배열로 저장	
			// String[] cmd = command.split(" "); System.out.println(cmd.length);
			 

			if (command.equalsIgnoreCase("exit")) {
				System.out.println("프로그램을 종료합니다.");
				break;
				
			} else if (command.startsWith("new ")) {
				// 회원 가입
				app.newCommand(command.split(" "));
				continue;
				
			} else if (command.startsWith("change ")) {
				// 전체 회원 정보 수정(암호)
				app.changeCommand(command.split(" "));
				continue;
				
			} else if(command.equalsIgnoreCase("list")) {
				// 전체 회원 정보 조회
					app.listCommand();
					continue;
				
			} // end if
       
       } // end while 
       
    } // end main


    public void newCommand(String[] commands) {
    	// 회원 가입
		// 처음 키워드가 new이므로 commands[0]은 생략한다
		//RegisterRequest DTO에 저장
    	
		RegisterRequest req = new RegisterRequest();
		req.setEmail(commands[1]);
		req.setName(commands[2]);
		req.setPassword(commands[3]);
		req.setPasswordConfirm(commands[4]);
			
		// MemberRegisterService regSvc = factory.getMemberRegisterService();
		// regSvc.register(req); 
		// 아래와 동일
		factory.getMemberRegisterService().register(req);;

    }
    
    public void changeCommand(String[] commands) {
    	// 전체 회원 정보 수정(암호)
    	
    	
		factory.getChangePasswordService().changePassword(commands[1], commands[2], commands[3]);
		System.out.println("암호를 변경하였습니다.");
    }
    
    
    public void listCommand() {
    	// 전체 회원 정보 조회
    	
    	
    	Collection<Member> member =  factory.getMemberRegisterService().selectAll();
		for(Member mem : member) {
			System.out.println(mem.getId() + "\t" + mem.getName() + "\t" +
								mem.getEmail() + "\t" + mem.getRegisterDate() +
								mem.getPassword());
		}
  
    }


}
