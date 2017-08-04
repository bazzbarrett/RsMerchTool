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
public class RSItemSet {
    String setName;
    RSItem[] items;
    RSItem setItem;
    int[] ids;
    int setId;
    
    public RSItemSet(int[] ids,int setId, String name){
        this.ids = ids;
        this.setId = setId;
        setName = name;
        fillSet();
    }
    
    public void fillSet(){
        items = new RSItem [ids.length];
        for (int i = 0; i < ids.length; i++) {
            items[i] = new RSItem(String.valueOf(ids[i]), true);
        }  
        setItem = new  RSItem(String.valueOf(setId), true);
    }
    
    public void getData(){
        for (int i = 0; i < ids.length; i++) {
            items[i].getData();
        }
        setItem.getData();
    }
    
    public Boolean allDone(){
        Boolean res = true;
        for (int i = 0; i < ids.length; i++) {
            if (items[i].getDataDone() == false) {
                return false;
            }
        }
        if (setItem.getDataDone() == false) return false;
        return true;
    }
    
    public void fillData(){
        for (int i = 0; i < ids.length; i++) {
            items[i].fillData();
        }
        setItem.fillData();
    }
    
    public int getCosts(){
        int total = 0;
        for (RSItem item : items) {
            total += (int)(item.curSellPrice());
        }
        return total;
    }
    
    public int getProfit(){
        return (int)setItem.curPrice() - getCosts(); 
    }
    public void print(){
        for (RSItem item : items) {
            System.out.println(item.name + " = " + (item.curPrice() - item.getBuyStd()));
        }
        System.out.println("Total = " + getCosts());
        System.out.println("Set = " + (int)setItem.curPrice());
        System.out.println("Profit = " + getProfit());
    }
    
}
