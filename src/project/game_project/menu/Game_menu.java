package project.game_project.menu;


import project.game_project.cache.Decorate;
import project.game_project.login.Login;
import project.game_project.cache.User;
import project.game_project.cache.Bag;
import project.game_project.game_service.Mypage;
import project.game_project.game_service.Dungeon;
import project.game_project.game_service.Item_service;
import project.game_project.game_service.Shop_service;

import java.util.Scanner;
import java.util.Random;


public class Game_menu{
    
    Scanner sc = new Scanner(System.in);
    Decorate decorate = new Decorate();
    User user = User.getInstance();
    
    
    public void game_menu(){
        stage_info(user.getStage());
        System.out.println(decorate.bar);
        
        while(true){
            //DB 에 저장하는코드
            decorate.println("게임 메뉴 - 비정상적으로 프로그램 종료시 저장이 안됩니다.");
            decorate.println("1.마이페이지 / 2.던전 입장 / 3.가방 / 4.스킬(개발중) / 5.상점 / 6.로그인 화면으로 이동");
            System.out.println(decorate.bar);
            int choice = sc.nextInt();
            System.out.println(decorate.bar);
            
            switch(choice){
                case 1:{
                    mypage();
                    break;
                }
                case 2:{
                    dungeon();
                    break;
                }
                case 3:{
                    bag();
                    break;
                }
                case 4:{
                    break;
                }
                case 5:{
                    shop();
                    break;
                }
                case 6:{
                    return;
                }
            }
            
        }
        
    }
    
    public void shop(){
        Shop_service shop_service = new Shop_service();
        
        while(true){
            System.out.println(decorate.bar);
            decorate.println("1. 상점 목록 보기 / 2. 상점 카테고리별 보기 / 3. 상점에다가 물건 팔기 / 4.검색 / 4. 뒤로가기");
            System.out.println(decorate.bar);
            int choice = sc.nextInt();
            
            switch(choice){
                case 1:{
                    shop_service.select_shop();
                    break;
                }
                case 2:{
                    shop_service.select_cate();
                    break;
                }
                case 3:{
                    
                    break;
                }
                case 4:{
                    
                    break;
                }
                case 5:{
                    return;
                }
            }
            
        }
    }
    
    public void bag(){
        Item_service item_service = new Item_service();
        
        while(true){
            System.out.println(decorate.bar);
            decorate.println("1.가방 보기 / 2.가방에서 아이템 착용|벗기 / 3.뒤로 가기");
            System.out.println(decorate.bar);
            int choice = sc.nextInt();
        
            switch(choice){
                case 1:{
                    item_service.select_bag_item();
                    break;
                }
                case 2:{
                    item_service.item_wearing();
                    break;
                }
                case 3:{
                    return;
                } 
                
            }
        }
        
    }
    
    public void dungeon(){
        Dungeon dungeon = new Dungeon();
        dungeon.getMonster().random_monster(user.getStage()); //몬스터 랜덤으로 가져오기
        System.out.println(decorate.bar);
        decorate.println("스테이지 - "+user.getStage()+" : 입장");
        decorate.println(dungeon.getMonster().getName()+" 몬스터가 등장하였다!");
        System.out.println(decorate.bar);
        decorate.println("1. 싸운다 / 2. 도망");
        int choice = sc.nextInt();
        switch(choice){
            case 1:{
                dungeon.fight();
                break;
            }
            case 2:{
                Random random = new Random();
                int percentage = random.nextInt(3);
                if(percentage == 1){
                    decorate.println("무사히 잘 도망쳤다!");
                    dungeon.fight();
                    return;
                }else{
                    decorate.println("도망치지 못해 전투를 준비한다..");
                    dungeon.fight();
                }
                break;
            }
        }
        
        
    }
    
    public void mypage(){
        
        Mypage page = new Mypage();
        
        decorate.println("1.나의 정보 보기 / 2.나의 회원정보 수정");
        System.out.println(decorate.bar);
        int choice = sc.nextInt();
        
        switch(choice){
            case 1:{
                page.info();
                break;
            }case 2:{
                page.update_info();
                break;
            }
        }

        
    }
    
    
    public void stage_info(int stage){
        
        switch(stage){
            case 1:{
                decorate.println("스테이지 - 1");
                break;
            }
            case 2:{
                decorate.println("스테이지 - 2");
                break;
            }
            case 3:{
                decorate.println("스테이지 - 3");
                break;
            }
            case 4:{
                decorate.println("스테이지 - 4");
                break;
            }
            case 5:{
                decorate.println("스테이지 - 5");
                break;
            }
        }
        
        
    }
    
    
}