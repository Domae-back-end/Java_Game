package project.game_project.entity;


public class Item {
    
    private long pid;
    private String name;
    private String info;
    private int level; //cut_level
    private int hp;
    private int ap;
    private int def;
    private int mp;
    private int money;
    private int per; //확률
    private String rank;
    
    
    public void setPid(long pid){
        this.pid = pid;
    }
    public long getPid(){
        return pid;
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
    
    
    public void setPer(int per){
        this.per = per;
    }
    public int getPer(){
        return per;
    }
    
    public void setRank(String rank){
        this.rank = rank;
    }
    public String getRank(){
        return rank;
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
    
    public void setLevel(int level){
        this.level = level;
    }
    public int getLevel(){
        return level;
    }
    
}