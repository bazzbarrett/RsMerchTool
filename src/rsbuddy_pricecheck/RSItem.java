/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rsbuddy_pricecheck;

import java.util.logging.Level;
import java.util.logging.Logger;
import static rsbuddy_pricecheck.JSON.getLongArray;
import static rsbuddy_pricecheck.misc.*;

/**
 *
 * @author bazzb
 */
public class RSItem {
    long[] bprices;
    long[] sprices;
    long[] ts;
    long[] bqty;
    long[] sqty;
    String name;
    String id;
    private URLThread urlt;
     
    public Boolean getDataDone(){
        return urlt.done;
    }
    
    public RSItem(){
    }
    
    public RSItem(String id, Boolean DontFill){
        this.id = id;
        if(DontFill == false){
            getData();
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    Logger.getLogger(RSItem.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (getDataDone()){
                    fillData();
                    return;
                }
            }
            System.out.println("Never filled data for : " + this.name);
        }
    }
    
    public void getData(){
        //System.out.println("getting data for " + id);
        this.name = IDS.getName(id);
        urlt = new URLThread("https://api.rsbuddy.com/grandExchange?a=graph&i=" + id);  
        urlt.run();
    }
    
    public void fillData(){
        if (getDataDone()) {
            String json = urlt.result;
            fillArrays(json);
            urlt = null;
        } else {
            System.out.println("Attempting to fill before get is complete!");
        } 
    }
    
    
    public void FillFromFile(String id, String itemName){
        this.name = itemName;
        this.id = id;
        String json = getFile("D:\\OSBuddyData\\" + id + ".json");
        fillArrays(json);
    }
    
    private void fillArrays(String json){
        bprices = getLongArray(json, "buyingPrice");
        sprices = getLongArray(json, "sellingPrice");
        bqty = getLongArray(json, "buyingCompleted");
        sqty = getLongArray(json, "sellingCompleted");
        ts = getLongArray(json, "ts");    
    }
    
    
    public double startPrice(){
        double res = -1;
        if (isTraded()) {
            res = 0;
            for (int i = 0; i < 3; i++) {
                res += bprices[i];
            }
            res = res/3;
        }
        return res;
    }
    
    public void printLast10(){
        int from = bprices.length -10;
        if (from < 0) from = 0;
        for (int i = from; i < bprices.length-1; i++) {
            System.out.println(bprices[i] + " x " + bqty[i]);
        }
    }
    
    public void printLast10sell(){
        int from = sprices.length -10;
        if (from < 0) from = 0;
        for (int i = from; i < sprices.length-1; i++) {
            System.out.println(sprices[i] + " x " + sqty[i]);
        }
    }
    
    public String[] getLast10buy(){
        String[] res = new String[10];
        int from = bprices.length -10;
        if (from < 0) from = 0;
        int j = 0;
        for (int i = from; i < bprices.length-1; i++) {
            res[j] = bprices[i] + " x " + bqty[i];
            j++;
        }  
        return res;
    }
    
    public String[] getLast10Sell(){
        String[] res = new String[10];
        int from = sprices.length -10;
        if (from < 0) from = 0;
        int j = 0;
        for (int i = from; i < sprices.length-1; i++) {
            res[j] = sprices[i] + " x " + sqty[i];
            j++;
        }  
        return res; 
    }
    
    public double curPrice(){
        return misc.calcProf(bprices);
    }
    
    public double curSellPrice(){
        return misc.calcProf(sprices);
    }
        
    public double getTotalBuy(){
        return misc.getSum(bqty);
    }
    
    public double getBuyAvg(){
        return misc.getAverage(bprices, bqty);
    }
    
    public double getSellAvg(){
        return misc.getAverage(sprices, sqty);
    }
    
    public double getBuyStd(){
        return misc.getStdDev(bprices, bqty);
    }
    
    public void fillVoids(){
        if (sprices[0] == -1) {sprices[0] = bprices[0];}
        if (bprices[0] == -1) {bprices[0] = sprices[0];}
        
        for (int i = 0; i < bprices.length; i++) {
           // ts[i] = toExcelTime(ts[i]);
            if (bprices[i] == -1) {bprices[i] = bprices[i-1];}
            if (sprices[i] == -1) {sprices[i] = sprices[i-1];}
        }
    }
    public void showGraph(){
        fillVoids();
        graph g = new graph(name, toHours(ts), bprices, sprices);
    }
    
    public double avgProfit(){
        return getBuyAvg() - getSellAvg();
    }
    
    public boolean isTraded(){
        return (bprices.length > 3);
    }
    
    public void show24hGraph(){
        fillVoids();
        double[] th = toHours(ts);
        int mark = 0;
        for (int i = 0; i < bprices.length; i++) {
            if (th[i] > -24){
                mark = i;
                break;
            }
        }
        double[] tmpth = new double[th.length - mark];
        long[] tmpbp = new long[th.length - mark];
        long[] tmpsp = new long[th.length - mark];
        for (int i = mark; i < th.length; i++) {
            tmpth[i-mark] = th[i];
            tmpbp[i-mark] = bprices[i];
            tmpsp[i-mark] = sprices[i];
        }
        graph g = new graph(name, tmpth, tmpbp, tmpsp);
    }
}
