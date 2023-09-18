package project.game_project.game_service;

import java.util.Scanner;

import project.game_project.entity.Monster;
import project.game_project.cache.Decorate;

public class User_Fight implements Runnable {
    
    private boolean winner = false;
    
    private String name;
    private int speed;
    private int hp;
    private int ap;
    private int def;
    private int mp;
    Monster monster;
    Scanner sc = new Scanner(System.in);
    Decorate decorate = new Decorate();
    
    public User_Fight(String name,int speed, int hp, int ap, int def, int mp,Monster monster){
        this.speed = speed;
        this.hp = hp;
        this.ap = ap;
        this.def = def;
        this.name = name;
        this.mp = mp;
        this.monster = monster;
    }
    
    public void run() {
        while (!Thread.currentThread().isInterrupted()) { // 승리할 때까지 반복
            // 게임 로직 구현
            System.out.println(decorate.bar);
            System.out.println("1.기본 공격 / 2.스킬 ( 준비중 ..)");
            System.out.println(decorate.bar);
            int choice = sc.nextInt();
            
            switch(choice){
                case 1:{
                    attack();
                    break;
                }
                case 2:{
                    break;
                }
            }
            if(monster.getHp() <= 0){
                winner = true;
                Thread.currentThread().interrupt();
                break;
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
        System.out.println(monster.getName()+"에게 "+ap+" 데미지를 주었습니다.");
        monster.setHp(monster.getHp() - (ap - monster.getDef()));
        System.out.println("현재 몬스터 남은 피 : "+monster.getHp());
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