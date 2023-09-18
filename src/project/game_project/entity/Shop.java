package project.game_project.entity;


import project.game_project.entity.Item;
import project.game_project.maria_db.DB_Connetion;

public class Shop {
    
    private long pid;
    private long item_pid;
    private Item item;
    
    
    public Item getItem(){
        return item;
    }
    
    public void setPid(long pid){
        this.pid = pid;
    }
    public long getPid(){
        return this.pid;
    }
    
    public void setItem_pid(long item_pid){
        this.item_pid = item_pid;
        this.item = new DB_Connetion().select_item(item_pid);
    }
    public long getItem_pid(){
        return this.item_pid;
    }
}