package project.game_project.game_service;

import project.game_project.cache.Decorate;

public class Monster_Fight implements Runnable {
    
    
    private boolean winner = false;
    
    private String name;
    private int speed;
    private int hp;
    private int ap;
    private int def;
    private int mp;
    User_Fight user_fight;
    Decorate decorate = new Decorate();
    
    public Monster_Fight(String name,int speed, int hp, int ap, int def, int mp, User_Fight user_fight){
        this.speed = speed;
        this.hp = hp;
        this.ap = ap;
        this.def = def;
        this.name = name;
        this.mp = mp;
        this.user_fight = user_fight;
    }
    
    public void run() {
        while (!Thread.currentThread().isInterrupted()) { // 승리할 때까지 반복
            // 게임 로직 구현
            attack();
            if(user_fight.getHp() <= 0){
                winner = true;
                Thread.currentThread().interrupt();
                return;
            }
            try {
                Thread.sleep(speed * 1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    
    public void attack(){
        System.out.println(decorate.bar);
        System.out.println(name+"에게서 "+ap+"만큼 데미지를 받았습니다.");
        user_fight.setHp(user_fight.getHp() - (ap - user_fight.getDef()));
        System.out.println("현재 자신의 남은 피 : "+user_fight.getHp());
        System.out.println(decorate.bar);
    }
    
    
    public void setMp(int mp){
        this.mp = mp;
    }
    public int getMp(){
        return mp;
    }
    
    public void setDef(int def){
        this.def = def;
    }
    public int getDef(){
        return def;
    }
    
    public void setAp(int ap){
        this.ap = ap;
    }
    public int getAp(){
        return ap;
    }
    public void setHp(int hp){
        this.hp = hp;
    }
    public int getHp(){
        return hp;
    }
    
    public void setSpeed(int speed){
        this.speed = speed;
    }
    public int getSpeed(){
        return speed;
    }
    
    public boolean getWinner(){
        return winner;
    }
    
}