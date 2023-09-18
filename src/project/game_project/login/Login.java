package project.game_project.login;

import java.util.Random;
import project.game_project.maria_db.DB_Connetion;

public class Login{
    
    DB_Connetion db = new DB_Connetion();
    
    
    public boolean login(String id, String pw){
        return db.login(id,pw);
    }
    
    public void register(String name, String nickname, String id, String pw, String email){
        db.register(getToken(), name,nickname, id, pw ,email);
    }
    
    
    public String getToken(){
        
        Random random = new Random();
        
        String result = "";
        
        while(true){
            result = random.ints(48,122 + 1)
                                      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                                      .limit(50)
                                      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                                      .toString();
            if(!(db.getToken_list(result))){
                return result;
            }
        }
        
    }
    
}