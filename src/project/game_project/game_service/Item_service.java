package project.game_project.game_service;

import project.game_project.cache.Decorate;
import project.game_project.cache.User;
import project.game_project.cache.Bag;
import project.game_project.cache.Wearing;
import project.game_project.maria_db.DB_Connetion;
import project.game_project.entity.Item;

import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;
import java.util.Random;

public class Item_service {
    
    DB_Connetion db = new DB_Connetion();
    Decorate decorate = new Decorate();
    Scanner sc = new Scanner(System.in);
    User user = User.getInstance();
    Bag bag = Bag.getInstance();
    Wearing wearing = Wearing.getInstance();
    HashMap<String, Item> map = new HashMap<>();
    
    //delete_wearing 오류
    //select_bag_item 오류 해결할것.
    
    
    
    public void item_get_check(long item_pid){
        //가방 슬롯
        if(bag.getSlot() <= bag.getItems().split(",").length){
            decorate.println("가방 슬롯이 부족하여 아이템을 얻지 못하였습니다.");
            System.out.println(decorate.bar);
        }else{
            Item item = db.select_item(item_pid);
            int per = item.getPer();
            Random r = new Random();
            
            if(r.nextInt(100) < per){
                //100 분에 per
                decorate.println("아이템을 발견하였습니다!");
                System.out.println(decorate.bar);
                
                if(bag.getItems() != null){
                    bag.setItems(bag.getItems()+","+item.getPid());
                }else{
                    bag.setItems(""+item.getPid());
                }
            }else{
                decorate.println("아이템을 발견하지 못하였습니다.");
                System.out.println(decorate.bar);
            }
            
        }
    }
    
    
    public void item_wearing(){
        System.out.println(decorate.bar);
        
        
        decorate.println("1. 가방에서 아이템 입기 / 2. 가방에서 아이템 벗기 / 3. 되돌아가기");
        System.out.println(decorate.bar);
        int choice = sc.nextInt();
        
        switch(choice){
            case 1:{
                select_bag_item();
                System.out.println(decorate.bar);
                decorate.println("착용할 아이템 번호를 입력해주세요. ex)0,1");
                System.out.println(decorate.bar);
                update_wearing();
                break;
            }case 2:{
                if(wearing.getItems() == null){
                    decorate.println("벗으실 아이템이 없습니다.");
                    break;
                }
                select_bag_item();
                System.out.println(decorate.bar);
                decorate.println("벗으실 아이템 번호를 입력해주세요. ex)0,1");
                System.out.println(decorate.bar);
                    
                delete_wearing();
                
                break;
            }case 3:{
                return;
            }default:{
                decorate.println("잘못 입력하여 되돌아갑니다.");
                return;
            }
                
        }
        
    }
    
    public void update_wearing(){
        
        String wearing_index = sc.next();
        
        //여러개 선택
        if(wearing_index.contains(",")){
            String[] item_list = wearing_index.split(",");

            //입력한것이 내 가방에 있는지 검사.
            HashMap<String, Item> buf = map;
            for(String a : item_list){
                boolean bag_check = true;
                
                for (Map.Entry<String, Item> entry : buf.entrySet()) {
                    if(entry.getValue() != null){
                        if(entry.getKey().equals(a)){
                            bag_check = false;
                            break;
                        }
                    }
                }
                
                if(bag_check){    
                    decorate.println("존재하지 않은 가방의 아이템이거나 잘못 입력 하셨습니다.");
                    return;
                }
            }
                
            //내 가방에 아이템이 내 레벨에 맞는 아이템인지 확인.
            for(int i = 0; i < item_list.length; i++) {
                Item buf_item = db.select_item(Long.parseLong(item_list[i]));
                if(buf_item.getLevel() > user.getLevel()){
                    decorate.println(buf_item.getName()+"의 아이템의 레벨이 높아 입을수 없습니다.");
                    return;
                }
            }
            //다 확인함.
            //출력용
            for(int i = 0; i < item_list.length; i++) {
                Item buf_item = buf.get(item_list[i]);
                if(buf_item.getLevel() > user.getLevel()){
                    decorate.println("아이템 레벨이 더 높아서 입을수가 없습니다.");  
                }else{
                    decorate.println(buf_item.getName()+"의 아이템을 입었습니다.");    
                }
            }
            
            //값 넣기.
            if(wearing.getItems() != null){
                String total = "";
                for(int i = 0; i < item_list.length; i++){
                    System.out.println("twest : "+item_list[i]);
                    Item buf_item = map.get(item_list[i]);
                    total += ","+buf_item.getPid();
                }
                wearing.setItems(wearing.getItems()+total);
                System.out.println("?! : "+total);
            }else{
                String tot="";
                for(String a : item_list){
                    for (Map.Entry<String, Item> entry : buf.entrySet()) {
                        if(entry.getValue() != null){
                            if(entry.getKey().equals(a)){
                                if(tot.equals("")){
                                    tot += ""+entry.getValue().getPid();
                                }else{
                                    tot += ","+entry.getValue().getPid();
                                }
                                break;
                            }
                        }
                    }
                }
                wearing.setItems(tot);
            }
            
        }else{
            //하나 선택
            HashMap<String, Item> buf = map;
            for (Map.Entry<String, Item> entry : buf.entrySet()) {
                if(entry.getValue() != null){
                    if(entry.getKey().equals(wearing_index)){
                        if(entry.getValue().getLevel() > user.getLevel()){
                            decorate.println("아이템 레벨이 더 높아서 입을수가 없습니다.");  
                            return;
                        }
                        decorate.println(entry.getValue().getName()+"의 아이템을 입었습니다.");
                        if(wearing.getItems() != null){
                            wearing.setItems(wearing.getItems()+","+wearing_index);
                        }else{
                            wearing.setItems(wearing_index);
                        }
                        return;
                    }
                }
            }
            decorate.println("존재하지 않은 가방의 아이템이거나 잘못 입력 하셨습니다.");
            
        }
        
        
    }
    
    public void delete_wearing(){
        
        String wearing_index = sc.next();
        
        //기능 개발.
        
        //여러개 선택
        if(wearing_index.contains(",")){
            
            
            if(wearing.getItems() != null){
                String[] wearing_index_arr = wearing_index.split(",");
                String[] wearing_pids = wearing.getItems().split(",");
                
                String[] delete_tot = new String[wearing_pids.length];
                
                for(String choice : wearing_index_arr){
                    Item item = map.get(choice);
                    boolean check = true;
                    for(int i = 0 ; i < wearing_pids.length; i++){
                        if(wearing_pids[i].equals(""+item.getPid())){
                            delete_tot[i] = ""+item.getPid(); //벗은 아이템 추가.
                            check = false;
                            wearing_pids[i] = "0";
                            break;
                        }
                    }
                    if(check){
                        decorate.println("아이템을 입고 있는 옷의 번호가 아니거나 잘못 입력하셨습니다.");
                        return;
                    }
                }
                wearing_pids = wearing.getItems().split(",");
                //출력
                for(String delete : delete_tot){
                    for(int i = 0; i < wearing_pids.length; i++){
                        if(delete != null){
                            if(delete.equals(wearing_pids[i])){
                                Item item = db.select_item(Long.parseLong(wearing_pids[i]));
                                decorate.println(item.getName()+"의 아이템을 벗었습니다.");
                                wearing_pids[i] = "0";
                                break;
                            }
                        }
                    }
                    
                }
                
                
                //데이터
                String tot = "";
                for(int i = 0; i < wearing_pids.length; i++){
                    if(wearing_pids[i] != null){
                        if(!(wearing_pids[i].equals("0"))){
                            if(tot.equals("")){
                                tot = wearing_pids[i];
                            }else{
                                tot += ","+wearing_pids[i];
                            }
                        }
                    }
                }
                wearing.setItems(tot);
                
                
            }else{
                decorate.println("입고 있는 옷이 없습니다.");
            }
        }else{
        //한개 선택
            
            //입고 있는 옷 O / X
            if(wearing.getItems() != null){
                
                String[] wearing_pids = wearing.getItems().split(",");
                
                Item item = map.get(wearing_index);
                boolean check = true;
                
                for(int i = 0 ; i < wearing_pids.length; i++){
                    if(wearing_pids[i].equals(""+item.getPid())){
                        check = false;
                        wearing_pids[i] = "0";
                        break;
                    }
                }
                if(check){
                    decorate.println("아이템을 입고 있는 옷의 번호가 아니거나 잘못 입력하셨습니다.");
                    return;
                }
                //출력
                decorate.println(item.getName()+"의 아이템을 벗었습니다.");
                
                //데이터
                String tot = "";
                for(int i = 0; i < wearing_pids.length; i++){
                    if(!(wearing_pids[i].equals("0"))){
                        if(wearing_pids[i] != null){
                            if(!(wearing_pids[i].equals(item.getPid()))){
                                if(tot.equals("")){
                                    tot = wearing_pids[i];
                                }else{
                                    tot += ","+wearing_pids[i];
                                }
                            }
                        }
                    }
                }
                wearing.setItems(tot);
            }else{
                decorate.println("입고 있는 옷이 없습니다.");
            }
        }
            
    }
    
    
    
    public void select_bag_item(){
        
        System.out.println(decorate.bar);
        decorate.println("가방에 있는 현재 아이템");
        System.out.println(decorate.bar);
        
        String[] wearing_pids={""};
        
        if(wearing.getItems() != null){
            wearing_pids = wearing.getItems().split(",");
        }
        
        if(bag.getItems() != null){
            String[] bag_items = bag.getItems().split(",");
            int index = 0;
            for(String a : bag_items){
                Item item = db.select_item(Long.parseLong(a));
                index++;
                System.out.println("번호 : "+index);
                System.out.print("아이템 이름 : ");
                decorate.println(""+item.getName());
                System.out.print("아이템 설명 : ");
                decorate.println(""+item.getInfo());
                System.out.print("아이템 체력증가 : ");
                decorate.println(""+item.getHp());
                System.out.print("아이템 공격증가 : ");
                decorate.println(""+item.getAp());
                System.out.print("아이템 방어력증가 : ");
                decorate.println(""+item.getDef());
                System.out.print("아이템 마나증가 : ");
                decorate.println(""+item.getMp());
                System.out.print("아이템 금액 : ");
                decorate.println(""+item.getMoney());
                System.out.print("아이템 착용 제한 레벨 : ");
                decorate.println(""+item.getLevel());
                System.out.print("아이템 등급 : ");
                decorate.println(""+item.getRank());
                map.put(""+index,item); //map 추가 부분.
                //착용 가능? 불가능?
                //착용중? 아님?
                if(!(wearing_pids[0].equals(""))) {
                    boolean check = true;
                    for(int i = 0; i < wearing_pids.length; i++){
                        if(wearing_pids[i].equals(""+item.getPid())){
                            decorate.println("이 아이템은 현재 착용중입니다.");
                            System.out.println(decorate.bar);
                            wearing_pids[i] = "0";
                            check = false;
                            break;
                        }
                    }
                    
                    if(check){
                        if(item.getLevel() > user.getLevel()){
                            decorate.println("아이템 레벨이 높아 착용 할수 없습니다.");
                            System.out.println(decorate.bar);
                        }else{
                            decorate.println("아이템을 착용할수 있습니다.");
                            System.out.println(decorate.bar);
                        }
                    }
                    
                }else{
                    if(item.getLevel() > user.getLevel()){
                        decorate.println("아이템 레벨이 높아 착용 할수 없습니다.");
                        System.out.println(decorate.bar);
                    }else{
                        decorate.println("아이템을 착용할수 있습니다.");
                        System.out.println(decorate.bar);
                    }
                }
                
            }
        }else{
            decorate.println("현재 가방에 있는 아이템이 없습니다.");
        }
        
    }
    
    
    
    
}