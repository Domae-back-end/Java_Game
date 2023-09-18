package project.game_project.cache;


public class Decorate{
    
    public String bar = "------------------------------------------------";
    
    
    public void println(String msg){
        try{
            
            String[] msg_array = msg.split("");
            
            for(int i=0;i<msg_array.length;i++){
                System.out.print(msg_array[i]);
                //Thread.sleep(50); test 빠르게 하기 위해
            }
            System.out.println();
        }catch(Exception e){
            
        }
    }
    public void print(String msg){
        try{
            
            String[] msg_array = msg.split("");
            
            for(int i=0;i<msg_array.length;i++){
                System.out.print(msg_array[i]);
                //Thread.sleep(50); test 빠르게 하기 위해
            }
        }catch(Exception e){
            
        }
    }
    
    
}