package project.game_project.main;

import project.game_project.cache.Decorate;
import project.game_project.cache.User;
import project.game_project.menu.Game_menu;
import project.game_project.menu.Main_menu;

public class Java_Game{
    
    
    public static void main(String[] args){
        
        Decorate test = new Decorate();
        Main_menu menu = new Main_menu();
        Game_menu game = new Game_menu();
        while(true){
            
            if(menu.login_menu()){
                //게임 시작!
                game.game_menu();
            }
            
        }
        
    }
    
}