package project.game_project.menu;

import java.util.Scanner;
import java.util.regex.Pattern;

import project.game_project.cache.Decorate;
import project.game_project.login.Login;
import project.game_project.cache.Cashe;
import project.game_project.login.Java_Mail;

public class Main_menu{
    
    Decorate decorate = new Decorate();
    int login_count = 1;
    
    public boolean login_menu(){
        
        Login login = new Login();
        Scanner sc = new Scanner(System.in);
        
        
        System.out.println(decorate.bar);
        decorate.println("RPG 게임 메인화면");
        System.out.println(decorate.bar);
        decorate.println("1. 로그인 / 2. 회원가입 / 3. 계정 찾기");
        System.out.println(decorate.bar);
        int choice = sc.nextInt();
        
        switch (choice) {
            case 1:{
                if(login_count == 4){
                    decorate.println("로그인 3회 실패로 계정찾기나 프로그램을 재 실행 해주세요.");
                    break;
                }
                decorate.println("아이디를 입력해주세요.");
                String id = sc.next();
                decorate.println("비밀번호를 입력해주세요.");
                String pw = sc.next();
                if(login.login(id,pw)){
                    System.out.println(decorate.bar);
                    decorate.println("로그인에 성공하였습니다.");
                    System.out.println(decorate.bar);
                    return true;
                }else{
                    System.out.println(decorate.bar);
                    decorate.println("로그인에 실패하였습니다.");
                    System.out.println(decorate.bar);
                    login_count++;
                }
                break;
            }case 2:{
                decorate.println("이름을 입력해주세요.");
                String name = sc.next();
                decorate.println("게임에서 사용할 닉네임을 입력해주세요.");
                String nickname = sc.next();
                decorate.println("아이디를 입력해주세요.");
                String id = sc.next();
                if(!(Pattern.matches("[0-9a-z]{0,15}",id))){
                    decorate.println("15글자 내로 작성 부탁드립니다.");
                    break;
                }
                decorate.println("비밀번호를 입력해주세요.");
                String pw = sc.next();
                if(!(Pattern.matches("[0-9a-z]{0,15}",pw))){
                    decorate.println("15글자 내로 작성 부탁드립니다.");
                    break;
                }
                decorate.println("이메일을 입력해주세요.");
                String email = sc.next();
                if(!(Pattern.matches("[0-9a-z_]*@naver.com",email))){
                    decorate.println("이메일 형식에 맞지 않습니다. / ex) OOOOO@naver.com");
                    break;
                }
                
                Java_Mail mail = new Java_Mail();
                mail.naverMailSend(email);
                
                decorate.println("이메일 인증 번호 입력해주세요.");
                String email_succes = sc.next();
                
                if(Cashe.getInstance().getEmail_succes().equals(email_succes)){
                    login.register(name,nickname,id,pw,email);
                    System.out.println(decorate.bar);
                    decorate.println("회원가입이 완료되었습니다! 로그인 해주세요!");
                    System.out.println(decorate.bar);
                    Cashe.getInstance().setEmail_succes(""); //이메일 인증 코드 초기화.
                    break;
                }else{
                    decorate.println("이메일 인증 코드가 맞지 않습니다.");
                    break;
                }
            }case 3:{
                
                break;
            }
        }
        
        
        return false;
    }
    
    
}