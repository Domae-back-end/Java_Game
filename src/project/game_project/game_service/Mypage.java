package project.game_project.game_service;

import project.game_project.cache.User;
import project.game_project.maria_db.DB_Connetion;
import project.game_project.cache.Decorate;
import project.game_project.cache.Wearing;

import java.util.Scanner;

public class Mypage {
    
    DB_Connetion db = new DB_Connetion();
    Decorate decorate = new Decorate();
    User user = User.getInstance();
    Wearing wearing = Wearing.getInstance();
    Scanner sc = new Scanner(System.in);
    
    
    public void update_nickname(String nickname){
        db.update_nickname(nickname);
        User.getInstance().setNickname(nickname);
    }
    
    public void update_info(){
                
                
                decorate.println("1.이름 수정하기 / 2.뒤로 돌아가기 / 추가할예정..");
                System.out.println(decorate.bar);
                int choice = sc.nextInt();
                
                switch(choice){
                    case 1:{
                        decorate.println("바꾸실 이름을 입력해주세요.");
                        System.out.println("현재 닉네임 : "+user.getNickname());
                        System.out.println(decorate.bar);
                        String change_nickname = sc.next();
                        update_nickname(change_nickname);
                        decorate.println("성공적으로 닉네임이 "+change_nickname+" 으로 변경되었습니다.");
                        break;
                    }
                    case 2:{
                        return;
                    }
                    case 3:{
                        break;
                    }
                    case 4:{
                        break;
                    }
                }
        
    }
    
    public void info(){
        
                decorate.println(user.getNickname()+" 님의 정보");
                System.out.println(decorate.bar);
                decorate.println("던전 단계");
                System.out.println(user.getStage());
                decorate.println("레벨");
                System.out.println(user.getLevel());
                decorate.println("경험치");
                System.out.println(user.getExp());

                System.out.println(decorate.bar);

                decorate.println("체력");
                System.out.println(user.getHp()+" + ("+wearing.getHp()+")");
                decorate.println("공격력");
                System.out.println(user.getAp()+" + ("+wearing.getAp()+")");
                decorate.println("방어력");
                System.out.println(user.getDef()+" + ("+wearing.getDef()+")");
                decorate.println("마나");
                System.out.println(user.getMp()+" + ("+wearing.getMp()+")");

                System.out.println(decorate.bar);

                decorate.println("잔액");
                System.out.println(user.getMoney());
                System.out.println(decorate.bar);
    }
    
}
