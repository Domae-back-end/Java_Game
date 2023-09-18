package project.game_project.maria_db;
    
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import project.game_project.cache.User;
import project.game_project.cache.Bag;
import project.game_project.cache.Wearing;
import project.game_project.entity.Item;
import project.game_project.entity.Shop;
import project.game_project.entity.Cate;

import java.util.ArrayList;
import java.util.HashMap;
 
public class DB_Connetion {
    
    String jdbc = "org.mariadb.jdbc.Driver";
    String url = "jdbc:mariadb://localhost:3306/game";
    String uid = "root";
    String pwd = "k1716k";
    
    
    
    public ArrayList<Cate> cate_list(){
        ArrayList<Cate> cate = new ArrayList();
        
        Connection con = null;
    	Statement smt = null;
    	ResultSet rs = null;
    	try {

    		Class.forName(jdbc);
    		con = DriverManager.getConnection(url,uid,pwd);
    		smt = con.createStatement();
    		rs = smt.executeQuery("select * from shop_cate");

            while(rs.next()){
                Cate cate_item = new Cate();
                
                cate_item.setCate(rs.getString("cate"));
                cate_item.setPid(rs.getLong("cate_pid"));
                cate_item.setShop_pid(rs.getLong("shop_pid"));
                cate.add(cate_item);
            }
            
        }catch (Exception ex) {
    		ex.printStackTrace();
    	}
    	finally {
    		try {
    			if(con!=null) rs.close();
    			if(con!=null) smt.close();
    			if(con!=null) con.close();
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	}
        return cate;
    }
    
    public String select_cate(long shop_pid){
        
        String cate="";
        
        Connection con = null;
    	Statement smt = null;
    	ResultSet rs = null;
    	try {
    		Class.forName(jdbc);
    		con = DriverManager.getConnection(url,uid,pwd);
    		smt = con.createStatement();
    		
    		rs = smt.executeQuery("select cate from shop_cate where shop_pid = '"+shop_pid+"'");
            cate = rs.getString("cate");
            
        }catch (Exception ex) {
    		ex.printStackTrace();
    	}
    	finally {
    		try {
    			if(con!=null) rs.close();
    			if(con!=null) smt.close();
    			if(con!=null) con.close();
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	}
        
        return cate;
    }
    
    public ArrayList<Shop> select_shop(){
        ArrayList<Shop> shop = new ArrayList<>();
        
        Connection con = null;
    	Statement smt = null;
    	ResultSet rs = null;
    	try {
    		Class.forName(jdbc);
    		con = DriverManager.getConnection(url,uid,pwd);
    		smt = con.createStatement();
    		
    		rs = smt.executeQuery("select * from shop");
            while(rs.next()){
                Shop shop_item = new Shop();
                shop_item.setPid(rs.getLong("shop_pid"));
                shop_item.setItem_pid(rs.getLong("item_pid"));
                shop.add(shop_item);
            }
            
        }catch (Exception ex) {
    		ex.printStackTrace();
    	}
    	finally {
    		try {
    			if(con!=null) rs.close();
    			if(con!=null) smt.close();
    			if(con!=null) con.close();
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	}
        return shop;
    }
    
    public Item select_item(long item_pid){
        Item item = new Item();
        
        Connection con = null;
    	Statement smt = null;
    	ResultSet rs = null;
    	try {
    		Class.forName(jdbc);
    		con = DriverManager.getConnection(url,uid,pwd);
    		smt = con.createStatement();
    		
    		rs = smt.executeQuery("select * from item where item_pid = '"+item_pid+"'");
            if(rs.next()){
                item.setPid(rs.getLong("item_pid"));
                item.setName(rs.getString("item_name"));
                item.setInfo(rs.getString("item_info"));
                item.setLevel(rs.getInt("item_cut_level"));
                item.setHp(rs.getInt("item_hp"));
                item.setAp(rs.getInt("item_ap"));
                item.setDef(rs.getInt("item_def"));
                item.setMp(rs.getInt("item_mp"));
                item.setMoney(rs.getInt("item_money"));
                item.setPer(rs.getInt("item_per"));
                item.setRank(rs.getString("item_rank"));
            }
            
        }catch (Exception ex) {
    		ex.printStackTrace();
    	}
    	finally {
    		try {
    			if(con!=null) rs.close();
    			if(con!=null) smt.close();
    			if(con!=null) con.close();
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	}
        
        return item;
    }
    
    public HashMap<String, String> select_random_monster(int stage) {
        
        HashMap<String, String> monster = new HashMap();
        Connection con = null;
    	Statement smt = null;
    	ResultSet rs = null;
    	try {
    		Class.forName(jdbc);
    		con = DriverManager.getConnection(url,uid,pwd);
    		smt = con.createStatement();
    		rs = smt.executeQuery("select * from monster where monster_stage = '"+stage+"'");
            
            int count = 0;
    		while(rs.next()){
                count++;
                monster.put("item_pid"+count,rs.getString("item_pid"));
                monster.put("name"+count,rs.getString("monster_name"));
                monster.put("stage"+count,rs.getString("monster_stage"));
                monster.put("info"+count,rs.getString("monster_info"));
                monster.put("level"+count,rs.getString("monster_level"));
                monster.put("exp"+count,rs.getString("monster_exp"));
                monster.put("ap"+count,rs.getString("monster_ap"));
                monster.put("speed"+count,rs.getString("monster_speed"));
                monster.put("hp"+count,rs.getString("monster_hp"));
                monster.put("def"+count,rs.getString("monster_def"));
                monster.put("mp"+count,rs.getString("monster_mp"));
                monster.put("money"+count,rs.getString("monster_money"));
            }
            monster.put("index",""+count);
            
        }

    	catch (Exception ex) {
    		ex.printStackTrace();
    	}
    	finally {
    		try {
    			if(con!=null) rs.close();
    			if(con!=null) smt.close();
    			if(con!=null) con.close();
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	}
        
        return monster;
    }
    
    public void update_nickname(String nickname){
        Connection con = null;
    	Statement smt = null;
    	ResultSet rs = null;
    	try {
    		Class.forName(jdbc);
    		con = DriverManager.getConnection(url,uid,pwd);
    		smt = con.createStatement();
    		
    		smt.executeQuery("UPDATE user_info SET user_nickname = '"+nickname+"' WHERE user_token = '"+User.getInstance().getToken()+"'");
    	}

    	catch (Exception ex) {
    		ex.printStackTrace();
    	}
    	finally {
    		try {
    			if(con!=null) smt.close();
    			if(con!=null) con.close();
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	}
        

    }
    
    public boolean getToken_list(String token){
        boolean check = false;
        Connection con = null;
    	Statement smt = null;
    	ResultSet rs = null;
    	try {
    		Class.forName(jdbc);
    		con = DriverManager.getConnection(url,uid,pwd);
    		smt = con.createStatement();
    		
    		rs = smt.executeQuery("select user_token from user where user_token = '"+token+"'");
    		
            check = rs.next();
    		
    	}

    	catch (Exception ex) {
    		ex.printStackTrace();
    	}
    	finally {
    		try {
    			if(con!=null) rs.close();
    			if(con!=null) smt.close();
    			if(con!=null) con.close();
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	}
        
        return check;

    }
    
    public boolean login(String id, String pw){
        
        boolean check = true;
        Connection con = null;
    	Statement smt = null;
    	ResultSet rs = null;
    	try {
            User user = User.getInstance();
    		Class.forName(jdbc);
    		con = DriverManager.getConnection(url,uid,pwd);
    		smt = con.createStatement();
    		
    		rs = smt.executeQuery("select * from user where user_id = '"+id+"' AND user_pw = '"+pw+"'");
    		
            check = rs.next();
            if(check){
                user.setToken(rs.getString("user_token"));
                rs = smt.executeQuery("select * from user_info where user_token = '"+user.getToken()+"'");

                if(rs.next()){
                    user.setNickname(rs.getString("user_nickname"));
                    user.setStage(rs.getInt("user_stage"));
                    user.setLevel(rs.getInt("user_level"));
                    user.setExp(rs.getInt("user_exp"));
                    user.setSpeed(rs.getInt("user_speed"));
                    user.setHp(rs.getInt("user_hp"));
                    user.setAp(rs.getInt("user_ap"));
                    user.setDef(rs.getInt("user_def"));
                    user.setMp(rs.getInt("user_mp"));
                }

                rs = smt.executeQuery("select * from user_bag where user_token = '"+user.getToken()+"'");

                Bag bag = Bag.getInstance();

                if(rs.next()){
                    bag.setSlot(rs.getInt("user_bag_slot"));
                    bag.setItems(rs.getString("user_bag_items"));
                }
                
                rs = smt.executeQuery("select * from wearing_item where user_token = '"+user.getToken()+"'");
                        
                if(rs.next()){
                    Wearing wearing = Wearing.getInstance();
                    wearing.setHp(rs.getInt("wearing_hp"));
                    wearing.setMp(rs.getInt("wearing_mp"));
                    wearing.setDef(rs.getInt("wearing_def"));
                    wearing.setAp(rs.getInt("wearing_ap"));
                    wearing.setItems(rs.getString("wearing_items"));
                }
                
            }
    	}

    	catch (Exception ex) {
    		ex.printStackTrace();
    	}
    	finally {
    		try {
    			if(con!=null) rs.close();
    			if(con!=null) smt.close();
    			if(con!=null) con.close();
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	}
        
        
        return check;
    }
    
    public void register(String token,String name, String nickname, String id, String pw, String email){
    	Connection con = null;
    	Statement smt = null;
    	ResultSet rs = null;
    	try {
    		Class.forName(jdbc);
    		con = DriverManager.getConnection(url,uid,pwd);
    		smt = con.createStatement();
    		
            //기본 셋팅
            
    		smt.executeQuery("INSERT INTO user("+
                             "user_id,user_pw,user_name,user_email,user_token,user_createday,user_joinday"+") VALUES ("+
                             "'"+id+"','"+pw+"','"+name+"','"+email+"','"+token+"',NOW(),NOW())");
            
            int stage = 1;
            int level = 1;
            int exp = 0;
            int ap = 10;
            int speed = 5;
            int hp = 100;
            int def = 0;
            int mp = 10;
    		smt.executeQuery("INSERT INTO user_info("+
                             "user_token,user_money,user_nickname,user_stage,user_level,user_exp,user_ap,user_speed,user_hp,user_def,user_mp,user_fix_day,user_joinday"+") VALUES ("+
                             "'"+token+"',0,'"+nickname+"','"+stage+"','"+level+"','"+exp+"','"+ap+"','"+speed+"','"+hp+"','"+def+"','"+mp+"',NOW(),NOW())");
            
            int bag_slot = 5;
            
    		smt.executeQuery("INSERT INTO user_bag("+
                             "user_token,user_bag_slot,user_bag_fix_day,user_bag_joinday"+") VALUES ("+
                             "'"+token+"','"+bag_slot+"',NOW(),NOW())");
            
            int skill_slot = 0;
    		smt.executeQuery("INSERT INTO user_skill("+
                             "user_token,user_skill_slot,user_skill_fix_day"+") VALUES ("+
                             "'"+token+"','"+skill_slot+"',NOW())");
            
            
    		smt.executeQuery("INSERT INTO wearing_item("+
                             "user_token,wearing_hp,wearing_ap,wearing_def,wearing_mp"+") VALUES ("+
                             "'"+token+"','0','0','0','0')");
    		
    		
    	}catch (Exception ex) {
    		ex.printStackTrace();
    	}
    	finally {
    		try {
    			if(con!=null) smt.close();
    			if(con!=null) con.close();
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	}
    }

    
}
