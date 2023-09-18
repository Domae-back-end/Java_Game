package project.game_project.entity;

import project.game_project.maria_db.DB_Connetion;
import project.game_project.cache.User;

import java.util.HashMap;
import java.util.Random;

public class Monster {
    
    DB_Connetion db = new DB_Connetion();
    
    private long item_pid;
    private int stage;
    private String name;
    private String info;
    private int level;
    private int exp;
    private int ap;
    private int speed;
    private int hp;
    private int def;
    private int mp;
    private int money;
    
    
    public void random_monster(int stage){
        HashMap<String, String> monster_info = db.select_random_monster(stage);
        
        Random random = new Random();
        System.out.println(monster_info.get("index"));
        int ran = random.nextInt(Integer.parseInt(monster_info.get("index")))+1;
        
        item_pid = Long.parseLong(monster_info.get("item_pid"+ran));
        stage = Integer.parseInt(monster_info.get("stage"+ran));
        name = monster_info.get("name"+ran);
        info = monster_info.get("info"+ran);
        level = Integer.parseInt(monster_info.get("level"+ran));
        exp = Integer.parseInt(monster_info.get("exp"+ran));
        ap = Integer.parseInt(monster_info.get("ap"+ran));
        speed = Integer.parseInt(monster_info.get("speed"+ran));
        hp = Integer.parseInt(monster_info.get("hp"+ran));
        def = Integer.parseInt(monster_info.get("def"+ran));
        mp = Integer.parseInt(monster_info.get("mp"+ran));
        money = Integer.parseInt(monster_info.get("money"+ran));
        
    }
    
    public void setItem(long item_pid){
        this.item_pid = item_pid;
    }
    public long getItem(){
        return item_pid;
    }
    
    
    public void setInfo(String info){
        this.info = info;
    }
    public String getInfo(){
        return info;
    }
    
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    
    public void setStage(int stage){
        this.stage = stage;
    }
    public int getStage(){
        return stage;
    }
    
    public void setLevel(int level){
        this.level = level;
    }
    public int getLevel(){
        return level;
    }
    
    public void setMoney(int money){
        this.money = money;
    }
    public int getMoney(){
        return money;
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
    
    public void setExp(int exp){
        this.exp = exp;
    }
    public int getExp(){
        return exp;
    }
}
