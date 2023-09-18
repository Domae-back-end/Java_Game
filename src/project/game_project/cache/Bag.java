package project.game_project.cache;


public class Bag{
    
    private static Bag bag;
    
    private int slot;
    private String items;
    
    public static Bag getInstance() {
        if(bag == null){
            bag = new Bag();
        }
        return bag;
    }
    
    public String getItems(){
        return items;
    }
    public void setItems(String items){
        this.items = items;
    }
    
    public int getSlot(){
        return slot;
    }
    public void setSlot(int slot){
        this.slot = slot;
    }
    
}