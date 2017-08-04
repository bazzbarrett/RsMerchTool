/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rsbuddy_pricecheck;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JList;
import org.json.JSONObject;
import static rsbuddy_pricecheck.JSON.createJsonFile;
import static rsbuddy_pricecheck.misc.getFile;
import static rsbuddy_pricecheck.misc.getURL;

/**
 *
 * @author bazzb
 */
public class IDS {
    public static String[] names;
    public static String[] ids;
    private static int[] store;
    
    
    public static String[] fillArray(String filter){
        ArrayList<String> l = new ArrayList<>();
        for (String name : names) {
            if (name.toLowerCase().contains(filter.toLowerCase())){
                l.add(name);
            }
        }
        String[] res = new String[l.size()];
        for (int i = 0; i < l.size(); i++) {
            res[i] = l.get(i);
        }
        return res;
    }
    
    public static void fillIds(){
        //String s = getFile("D:/OSBuddyData/names.json");
        String s = getURL("https://rsbuddy.com/exchange/names.json");
        JSONObject objs = new JSONObject(s);
        ids = JSONObject.getNames(objs);
        names = new String[ids.length];
        store = new int[ids.length];
        //JSONObject tmpObj = objs.get(s);
        for (int i = 0; i < ids.length; i++) {
            names[i] = objs.getJSONObject(ids[i]).getString("name").trim();
            //System.out.println(ids[i] + " - " + names[i]);
            store[i] = objs.getJSONObject(ids[i]).getInt("store");
        }  
    }  
    
    private static int getMark(String id){
        int mark = 0;
        for (int i = 0; i < ids.length; i++) {
            if (id.equals(ids[i])) {
                mark = i;
                break;
            }
        }
        return mark;    
    }
    
    public static String getName(String id){
        return names[getMark(id)];
    }
    
    public static String getID(String name){
        int mark = 0;
        for (int i = 0; i < names.length; i++) {
            if (name.equals(names[i])) {
                mark = i;
                break;
            }
        }
        return ids[mark];
    }
    
    public static int getStore(String id){
        return store[getMark(id)];
    }
    
    public static void downloadData(){
        int start = 2738;
        int end = ids.length;
//        for (int i = start; i < end; i++) {
//            URLThread t = new URLThread("https://api.rsbuddy.com/grandExchange?a=graph&i=" + ids[i]);
//            t.writeToFile(ids[i]);
//            t.run();
//            System.out.println(ids[i] + " = " + i + "/" + end);
//        }
        for (int i = start; i < end; i++) {
            createJsonFile("D:\\OSBuddyData\\" + ids[i] + ".json", getURL("https://api.rsbuddy.com/grandExchange?a=graph&i=" + ids[i]));
            System.out.println(ids[i] + " = " + i + "/" + end);
        }
    }
    
//    public static void alch(){
//        RSItem tmp;
//        for (int i = 0; i < ids.length; i++) {
//        tmp = new RSItem(ids[i], names[i],true);
//            if (tmp.isTraded()){
//                
//                System.out.println(ids[i] + "," + names[i] + "," + tmp.getBuyAvg() +"," + store[i] + "," + tmp.getTotalBuy());
//            }    
//        }
//    }
//    
//    public static void printData(){
//        fillIds();
//        RSItem tmp;
//        for (int j = 0; j < ids.length; j++) {
//            tmp = new RSItem(ids[j], names[j],true);
//            if (tmp.isTraded()){
//                
//                System.out.println(ids[j] + "," + names[j] + "," + tmp.startPrice() + "," + tmp.getBuyStd() + "," + tmp.getTotalBuy() + "," + tmp.getBuyAvg() +"," + tmp.curPrice());
//            }
//        }
//    }
        
//    public static void printProfits(){
//        RSItem tmp;
//        for (int j = 0; j < ids.length; j++) {
//            tmp = new RSItem(ids[j], names[j],true);
//            if (tmp.isTraded()){
//                
//                System.out.println(ids[j] + "," + names[j] + "," + tmp.curPrice() + "," + tmp.curSellPrice() + "," + tmp.getTotalBuy());
//            }           
//        }
//    }
    
}
