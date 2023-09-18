package project.game_project.game_service;

import project.game_project.maria_db.DB_Connetion;
import project.game_project.entity.Shop;
import project.game_project.entity.Item;
import project.game_project.entity.Cate;
import project.game_project.cache.Decorate;
import project.game_project.cache.User;
import project.game_project.cache.Bag;
import project.game_project.cache.Wearing;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Shop_service {
    
    DB_Connetion db = new DB_Connetion();
    Decorate decorate = new Decorate();
    Scanner sc = new Scanner(System.in);
    HashMap<String, Item> item_list = new HashMap();
    
    User user = User.getInstance();
    Bag bag = Bag.getInstance();
    
       
    public void select_cate(){
        ArrayList<Cate> cate_list = db.cate_list();
        
        if(!(cate_list.isEmpty())){
            //카테고리별 선택해서 보여주기.
            System.out.println(decorate.bar);
            decorate.println("하나의 카테고리 선택해주세요.");
            HashMap<String, Cate> map = new HashMap<>();
            for(int i = 0; i < cate_list.size(); i++){
                decorate.print((i+1)+". "+cate_list.get(i));
                map.put((i+1)+"",cate_list.get(i)); //Map 추가
            }
            System.out.println(decorate.bar);
            String choice = sc.next();
            System.out.println(decorate.bar);
            
            for (Map.Entry<String, Cate> entry : map.entrySet()) {
                if(entry.getKey().equals(choice)){
                    //입력
                    return;
                }
            }
            decorate.println("잘못된 선택입니다.");
            System.out.println(decorate.bar);
            
            
        }else{
            System.out.println(decorate.bar);
            decorate.println("현재 상점에 등록되어 판매하는 아이템이 없습니다.");
            System.out.println(decorate.bar);
        }
    }
    
    public void select_shop(){
        ArrayList<Shop> shop_list = db.select_shop();
        
        System.out.println(decorate.bar);
        decorate.println("1. 아이템 목록 / 2. 아이템 사기 / 3. 되돌아가기");
        System.out.println(decorate.bar);
        int choice = sc.nextInt();
        
        switch(choice){
            case 1:{
                select();
                break;
            }
            case 2:{
                select();
                buy();
                break;
            }
            case 3:{
                return;
            }
        }
        
    }    
    
    public void buy(){
        System.out.println(decorate.bar);
        decorate.println("아이템을 구매할 번호를 입력해주세요. ex) 1,2");
        System.out.println(decorate.bar);
        String choice = sc.next();
        
        //여러개 사기
        if(choice.contains(",")){
            String[] choice_arr = choice.split(",");
            for (Map.Entry<String, Item> entry : item_list.entrySet()) {
                for(String a : choice_arr){
                    if(entry.getKey().equals(a)){
                        //구매
                        Item shop_item = entry.getValue();

                        if(user.getMoney() >= shop_item.getMoney()){
                            //돈 있음

                            if(bag.getSlot() <= bag.getItems().split(",").length){
                                //가방 슬롯 부족
                                decorate.println(shop_item.getName()+" 아이템 현재 구매중...");
                                decorate.println("현재 캐릭터의 가방 슬롯이 부족하여 결제 실패...");
                            }else{
                                //살수 있음.
                                decorate.println(shop_item.getName()+" 아이템 현재 구매중...");
                                decorate.println(shop_item.getName()+" 아이템을 성공적으로 결제하였습니다.");
                                if(bag.getItems() != null){
                                    bag.setItems(bag.getItems()+","+shop_item.getPid());
                                }else{
                                    bag.setItems(""+shop_item.getPid());
                                }
                            }

                        }else{
                            //돈 없음.
                            decorate.println(shop_item.getName()+" 아이템 현재 구매중...");
                            decorate.println("현재 캐릭터의 잔액이 부족하여 결제 실패..");
                        }

                        return;
                    }                    
                }

            }
            decorate.println("아이템을 구매할 번호를 잘못 눌르셨습니다.");
        }else{
            //하나사기
            for (Map.Entry<String, Item> entry : item_list.entrySet()) {
                if(entry.getKey().equals(choice)){
                    //구매
                    Item shop_item = entry.getValue();
                    
                    if(user.getMoney() >= shop_item.getMoney()){
                        //돈 있음
                        
                        if(bag.getSlot() <= bag.getItems().split(",").length){
                            //가방 슬롯 부족
                            decorate.println(shop_item.getName()+" 아이템 현재 구매중...");
                            decorate.println("현재 캐릭터의 가방 슬롯이 부족하여 결제 실패...");
                        }else{
                            //살수 있음.
                            decorate.println(shop_item.getName()+" 아이템 현재 구매중...");
                            decorate.println(shop_item.getName()+" 아이템을 성공적으로 결제하였습니다.");
                            if(bag.getItems() != null){
                                bag.setItems(bag.getItems()+","+shop_item.getPid());
                            }else{
                                bag.setItems(""+shop_item.getPid());
                            }
                        }
                        
                    }else{
                        //돈 없음.
                        decorate.println(shop_item.getName()+" 아이템 현재 구매중...");
                        decorate.println("현재 캐릭터의 잔액이 부족하여 결제 실패..");
                    }
                    
                    return;
                }
            }
            decorate.println("아이템을 구매할 번호를 잘못 눌르셨습니다.");
            
        }
    }

    
    public void select(){
        int index = 0;
        ArrayList<Shop> shop_list = db.select_shop();
        if(!(shop_list.isEmpty())){
            
            for(Shop shop : shop_list){
                index++;
                Item item = shop.getItem(); //상점 아이템
                String cate = db.select_cate(shop.getPid()); //카테고리

                System.out.println(decorate.bar);
                decorate.println("카테고리 : "+cate);
                decorate.println("아이템 이름 : ");
                System.out.print(item.getName());
                decorate.println("아이템 설명 : ");
                System.out.print(item.getInfo());
                decorate.println("아이템 능력치 : ");
                System.out.println("hp : "+item.getHp()+" / ap : "+item.getAp()+" / def : "+item.getDef()+" / mp : "+item.getMp());
                decorate.println("아이템 착용 레벨 : ");
                System.out.print(item.getLevel());
                decorate.println("아이템 가격 : ");
                System.out.print(item.getMoney());
                System.out.println(decorate.bar);
                item_list.put(""+index,item);

            }
            
        }else{
            System.out.println(decorate.bar);
            decorate.println("현재 상점에 등록되어 판매하는 아이템이 없습니다.");
            System.out.println(decorate.bar);
        }
    }
    
    
    
    
}