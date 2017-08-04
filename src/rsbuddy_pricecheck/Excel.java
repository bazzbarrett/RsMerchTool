/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rsbuddy_pricecheck;

/**
 *
 * @author Alex Barrett <a.barrett@lancaster.ac.uk>
 */
public class Excel {
    public static void out(){
       // System.out.
        int max = IDS.names.length;
        //int max = 300;
        //RSItem[] itms = new RSItem[max];
        RSItem tmp;
        System.out.println("ID, Name, Qty, SellAvg, BuyAvg, BuyStdDev, AvgProfit, Cur Price");
        for (int i = 0; i < max-1; i++) {
            tmp = new RSItem();
            tmp.FillFromFile(IDS.ids[i], IDS.names[i]);
            System.out.println(tmp.id + ", " + tmp.name + ", " + tmp.getTotalBuy() + ", " + tmp.getSellAvg() + ", " + tmp.getBuyAvg() + ", " + tmp.getBuyStd() + ", " + tmp.avgProfit() + ", " + tmp.curPrice());
        }
    }
}
