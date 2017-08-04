/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rsbuddy_pricecheck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import static rsbuddy_pricecheck.JSON.createJsonFile;

/**
 *
 * @author bazzb
 */
public class URLThread implements Runnable{
    private String myURL;
    public String result;
    public Boolean done = false;
    private Boolean wtf = false; //Write to file
    private String id;
    
    public URLThread(String URL){
        myURL = URL;
    }
   
    public void writeToFile(String id){
        wtf = true;
        this.id = id;
    }
        
    @Override
    public void run() {
        StringBuilder sb = new StringBuilder();
        try {
            URL oracle = new URL(myURL);
            BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                sb.append(inputLine);
            in.close();
        } catch (MalformedURLException ex) {
            Logger.getLogger(misc.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(misc.class.getName()).log(Level.SEVERE, null, ex);
        }
        result = sb.toString(); 
        done = true;
        if (wtf) {
            createJsonFile("D:\\OSBuddyData\\" + id + ".json", result);
            System.out.println(id);
        }
    }
    
}
