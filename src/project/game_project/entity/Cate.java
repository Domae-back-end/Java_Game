package project.game_project.entity;


public class Cate {
    private long pid;    
    private long shop_pid;
    private String cate;
    
    public void setCate(String cate){
        this.cate = cate;
    }
    public String getCate(){
        return this.cate;
    }
    
    public void setPid(long pid){
        this.pid = pid;
    }
    public long getPid(){
        return this.pid;
    }
    
    public void setShop_pid(long shop_pid){
        this.shop_pid = shop_pid;
    }
    public long getShop_pid(){
        return this.shop_pid;
    }

}
