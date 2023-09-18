package project.game_project.game_service;

import project.game_project.cache.User;
import project.game_project.cache.Decorate;
import project.game_project.maria_db.DB_Connetion;
import project.game_project.entity.Monster;
import project.game_project.game_service.Item_service;
import project.game_project.cache.Wearing;

public class Dungeon {
    
    DB_Connetion db = new DB_Connetion();
    Monster monster = new Monster();
    
    Decorate decorate = new Decorate();
    User user = User.getInstance();
    Wearing wearing = Wearing.getInstance();
    Item_service item_service = new Item_service();
    public Monster getMonster(){
        return monster;
    }
    
    //싸움 시작.
    public void fight(){
        String monster_name = monster.getName();
        decorate.println(monster_name+" VS "+user.getNickname()+" / 대결 시작");
        //멀티쓰레드
        User_Fight user_fight = new User_Fight(user.getNickname(),user.getSpeed(),user.getHp()+wearing.getHp(),user.getAp()+wearing.getAp(),user.getDef()+wearing.getDef(),user.getMp()+wearing.getMp(),monster);
        Monster_Fight monster_fight = new Monster_Fight(monster_name,monster.getSpeed(),monster.getHp(),monster.getAp(),monster.getDef(),monster.getMp(),user_fight);
        
        Thread userThread = new Thread(user_fight);
        Thread monsterThread = new Thread(monster_fight);
        
        //멀티쓰레드 시작
        userThread.start();
        monsterThread.start();
        
        while(true){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(user_fight.getWinner()){
                //유저 승
                userThread.interrupt();
                monsterThread.interrupt();
                System.out.println(decorate.bar);
                decorate.println(monster_name+" 에게서 승리 하였다!!");
                decorate.println(monster.getMoney()+" 만큼 돈을 획득했다!");
                decorate.println(monster.getExp()+" 만큼 경험치를 획득했다!");
                System.out.println(decorate.bar);
                user.setMoney(user.getMoney() + monster.getMoney());
                user.setExp(user.getExp() + monster.getExp());
                //아이템 획득도 해야함. 아이템 테이블에 확률 있음.
                item_service.item_get_check(monster.getItem());
                
                return;
            }
            if(monster_fight.getWinner()){
                //몬스터 승
                userThread.interrupt();
                monsterThread.interrupt();
                System.out.println(decorate.bar);
                decorate.println("눈앞이 캄캄해 진다...");
                decorate.println(monster.getMoney()+" 만큼 돈을 잃었다....");
                System.out.println(decorate.bar);
                user.setMoney(user.getMoney() - monster.getMoney());
                return;
            }
            
        }
    }
    
    
}
