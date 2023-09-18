package project.game_project.cache;


public class User{
    
    private String nickname;
    private String token;
    private int stage;
    private int level;
    private int exp;
    private int speed;
    private int hp;
    private int ap;
    private int def;
    private int mp;
    private int money;
    private static User user;
    
    public static User getInstance() {
        if(user == null){
            user = new User();
        }
        return user;
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
    
    //레벨업
    public void setExp(int exp){
        this.exp = exp;
        if(getLevel() <= 5){
            if(this.exp >= 20){
                this.level += 1;
                this.exp = this.exp - 20;
            }
        }else if(getLevel() <= 10){
            if(this.exp >= 50){
                this.level += 1;
                this.exp = this.exp - 50;
            }
        }else if(getLevel() <= 20){
            if(this.exp >= 100){
                this.level += 1;
                this.exp = this.exp - 100;
            }
        }else if(getLevel() <= 30){
            if(this.exp >= 200){
                this.level += 1;
                this.exp = this.exp - 200;
            }
        }
    }
    public int getExp(){
        return exp;
    }
    
    public void setNickname(String nickname){
        this.nickname = nickname;
    }
    public String getNickname(){
        return nickname;
    }
    public void setToken(String token){
        this.token = token;
    }
    public String getToken(){
        return token;
    }
    
    public void setStage(int stage){
        this.stage = stage;
    }
    public int getStage(){
        return stage;
    }
    
    //레벨업에 따른 능력치 변화 및 스테이지 조절
    public void setLevel(int level){
        this.level = level;
        
        this.hp += 10;
        this.ap += 10;
        this.def += 10;
        this.mp += 10;
        
        //스테이지 조절
        if(getLevel() >= 10){
            //레벨 10 이상
            this.stage += 1;
            this.speed -= 1;
        }else if(getLevel() >= 20){
            this.stage += 1;
            this.speed -= 1;
            //레벨 20 이상
        }else if(getLevel() >= 30){
            this.stage += 1;
            this.speed -= 1;
            //레벨 30 이상
        }
    }
    public int getLevel(){
        return level;
    }
    
}