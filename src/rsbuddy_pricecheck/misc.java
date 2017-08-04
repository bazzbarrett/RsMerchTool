/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rsbuddy_pricecheck;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;
import static rsbuddy_pricecheck.misc.webClient;

/**
 *
 * @author bazzb
 */
public class misc {
    public static WebClient webClient;
    
    public static void sleep(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException ex) {
            Logger.getLogger(RSBuddy_PriceCheck.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void openClient(){
        webClient = new WebClient(BrowserVersion.CHROME);
       // new WebClient();
        try{
            webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
            String url = "https://rsbuddy.com/exchange/names.json";
            URL u = new URL(url);
            webClient.addRequestHeader("Content-Type","application/json");
            webClient.addRequestHeader("X-Auth-Key", "7254833235915b8e654b09905595bb1652410");
            webClient.addRequestHeader("X-Auth-Email","bazzbarrett1@hotmail.co.uk");
            Page htmlPage = webClient.getPage(url);
            //webClient.openWindow(u, url);
            webClient.waitForBackgroundJavaScript(7000);
            htmlPage = webClient.getPage(url);
            htmlPage.getWebResponse().getContentAsString();
        } catch (MalformedURLException ex) {
            Logger.getLogger(misc.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | FailingHttpStatusCodeException ex) {
            Logger.getLogger(misc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static String getURL(String myURL){
       
        try {
            //HtmlPage htmlPage = webClient.getPage("https://www.rsbuddy.com");
            //System.out.println(htmlPage.asText());
            //webClient.
            Page p = webClient.getPage(myURL);
            //p.getWebResponse().getContentAsString();
            String res = p.getWebResponse().getContentAsString();
            p.cleanUp();
            return res;
        } catch (IOException | FailingHttpStatusCodeException ex) {
            Logger.getLogger(misc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static String getURL2(String myURL) {
		//System.out.println("Requeted URL:" + myURL);
        URLConnection urlConn = null;
        InputStreamReader in = null;
		StringBuilder sb = new StringBuilder();
		try {
			URL url = new URL(myURL);
			urlConn = url.openConnection();
			if (urlConn != null)
				urlConn.setReadTimeout(60 * 1000);
			if (urlConn != null && urlConn.getInputStream() != null) {
				in = new InputStreamReader(urlConn.getInputStream(),
						Charset.defaultCharset());
				BufferedReader bufferedReader = new BufferedReader(in);
				if (bufferedReader != null) {
					int cp;
					while ((cp = bufferedReader.read()) != -1) {
						sb.append((char) cp);
					}
					bufferedReader.close();
				}
			}
		in.close();
		} catch (Exception e) {
			throw new RuntimeException("Exception while calling URL:"+ myURL, e);
		} 
 
		return sb.toString();
	}
    
        static double getSum(long[] arr){
        double total = 0; 
        for (long a : arr) {
            if (a != -1) {
                total += a;   
            } 
        } 
        return total;
    }
    
    public static String getURLold(String url){
         try (final WebClient webClient = new WebClient(BrowserVersion.CHROME)) {
            webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
            //webClient.setJavaScriptEngine(null);
            //String url = "http://www.rsbuddy.com/";
            HtmlPage htmlPage = null;
             try {
                 htmlPage = webClient.getPage(url);
             } catch (IOException | FailingHttpStatusCodeException ex) {
                 Logger.getLogger(misc.class.getName()).log(Level.SEVERE, null, ex);
             }
            webClient.waitForBackgroundJavaScript(10000);
            return htmlPage.asText();
        }
    }
        
    static double getAverage(long[] arr){
        double total = 0; 
        int c = 0;
        for (long a : arr) {
            if (a != -1) {
                total += a;   
                c++;
            } 
        }
        if (c < 1) return -1;
        return total/c;
    } 
    
    static double getAverage(long[] arr, long[] weight){
        double total = 0; 
        int c = 0;
        for (int i = 0; i < arr.length; i++){
            if (arr[i] != -1) {
                total += arr[i]*weight[i];   
                c += weight[i];
            } 
        }
        if (c < 1) return -1;
        return total/c;
    } 
    
    static double getStdDev(long[] arr){
        double total = 0; 
        long c = 0;
        double mean = getAverage(arr);
        if (mean == -1) return -1;
        for (long a : arr) {
            if (a != -1) {
                //System.out.println(a);
                total += (a-mean)*(a-mean);   
                c++;
            } 
        }
        //System.out.println(total);
        return Math.sqrt(total/c);
    }
    
    static long getLast(long[] arr){
        for (int i = arr.length-1; i > 1; i--) {
            if (arr[i] != -1) {
                return arr[i];
            }
        }
        return -1;
    }
    
    static double calcProf(long[] buy){
        int c = 0;
        double total = 0;
        for (int i = buy.length-1; i > 1; i--) {
            //system.out.println(buy[i]);
            if (buy[i] > 0) {
                //System.out.println(buy[i]);
                total += buy[i];
                c++;
                if (c > 4) break;
            }
        }
        return ((total/c));
    }
    
    static double getStdDev(long[] arr, long[] weight){
        double total = 0; 
        long c = 0;
        double mean = getAverage(arr, weight);
        if (mean == -1) return -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != -1) {
                //System.out.println(a);
                total += (arr[i]-mean)*(arr[i]-mean)*weight[i];   
                c += weight[i];
            } 
        }
        //System.out.println(total);
        return Math.sqrt(total/c);
    }
    
            public static String getFile(String loc){
        BufferedReader reader;
        try {
            reader = new BufferedReader( new FileReader(loc));
            String result = reader.readLine();
            reader.close();
            return result;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RSBuddy_PriceCheck.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RSBuddy_PriceCheck.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
            
                static double toExcelTime(long time){
        double tmp;
        tmp = (double)time/(1000*24*3600);
        return 25569 + tmp;
    }
    
    static double[] toHours(long[] ts){
        double latest = (double)ts[ts.length-1];
        double[] tmp = new double[ts.length];
        for (int i = 0; i < ts.length; i++) {
            tmp[i] = ((double)ts[i] - latest)/3600000;
        }
        return tmp;
    }
    
   
}
