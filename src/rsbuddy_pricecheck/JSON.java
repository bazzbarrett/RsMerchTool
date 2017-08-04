/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rsbuddy_pricecheck;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author bazzb
 */
public class JSON {
        public static long[] getLongArray(String s, String key){
        JSONArray jarray = new JSONArray(s);
        long[] result = new long[jarray.length()];
        for (int i = 0; i < jarray.length(); i++) {
            try{
                result[i] = jarray.getJSONObject(i).getLong(key);
            } catch(JSONException j){
                result[i] = -1;
            }
        }
        return result;
    }
   
    
    public static void writeJSON(String s){
        JSONObject obj = new JSONObject(s);
        //String pageName = obj.getJSONObject("pageInfo").getString("pageName");
       // System.out.println();
        System.out.print(obj.getInt("buying") + ", ");
        System.out.println(obj.getInt("selling"));
    }
    

        
    public static void createJsonFile(String dest, String JsonData){
        try {
            BufferedWriter writer = new BufferedWriter( new FileWriter(dest));
            writer.write(JsonData);
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(RSBuddy_PriceCheck.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
}
