/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rsbuddy_pricecheck;

/**
 *
 * @author bazzb
 */
public class Potion {
    RSItem pot3;
    RSItem pot4;
    int Id3;
    int Id4;
    public Potion(int id3, int id4) {
        Id3 = id3;
        Id4 = id4;
    }
    
    public void loadData(){
        pot3 = new RSItem(String.valueOf(Id3), false);
        pot4 = new RSItem(String.valueOf(Id4), false);    
    }
    
    public int GetQty(){
        return (int) pot3.getTotalBuy();
    }
    
    public int get3Price(){
        return (int) pot3.curSellPrice();
    }
    
    public int get4Price(){
        return (int) pot4.curPrice();
    }
    
    public int getProfit(){
        return (int)((get4Price()*3/4) - get3Price());
    }
    
    public String getName(){
        return pot3.name.substring(0, pot3.name.length()-3);
    }
    
    public int getPercentProfit(){
        return (int)(100* getProfit() / get3Price());
    }
}
