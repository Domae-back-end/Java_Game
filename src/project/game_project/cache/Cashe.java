package project.game_project.cache;


public class Cashe{
    
    private String email_succes;
    private static Cashe cashe;
    
    public static Cashe getInstance() {
        if(cashe == null){
            cashe = new Cashe();
        }
        return cashe;
    }
    
    public void setEmail_succes(String email_succes){
        this.email_succes = email_succes;
    }
    public String getEmail_succes(){
        return email_succes;
    }
    
}