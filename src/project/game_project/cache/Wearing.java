package project.game_project.cache;

import project.game_project.maria_db.DB_Connetion;
import project.game_project.entity.Item;
import project.game_project.cache.Decorate;

public class Wearing{
    
    private static Wearing wearing;
    private int hp=0;
    private int ap=0;
    private int def=0;
    private int mp=0;
    private String items;
    
    public static Wearing getInstance() {
        if(wearing == null){
            wearing = new Wearing();
        }
        return wearing;
    }
    
    //착용으로 인해 아이템의 능력치 적용 오류 발생
    public void setItems(String items){
        this.items = items;
        if(items != null){
            Decorate decorate = new Decorate();
            DB_Connetion db = new DB_Connetion();
            String[] item_arr = items.split(",");
            
            System.out.println(decorate.bar);
            decorate.println("몸에 변화를 느낀다....");
            decorate.println("hp : "+hp+", ap : "+ap+", def : "+def+", mp : "+mp);
            for(int i = 0; i < item_arr.length; i++) {
                Item buf_item = db.select_item(Long.parseLong(item_arr[i]));
                this.hp += buf_item.getHp();
                this.ap += buf_item.getAp();
                this.def += buf_item.getDef();
                this.mp += buf_item.getMp();
            }
            System.out.println("↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓");
            decorate.println("hp : "+this.hp+", ap : "+this.ap+", def : "+this.def+", mp : "+this.mp);
            System.out.println(decorate.bar);
        }
    }
    public String getItems(){
        return items;
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
}