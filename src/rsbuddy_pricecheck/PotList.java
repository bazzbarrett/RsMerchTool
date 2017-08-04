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
public class PotList {
    public static Potion[] pots;
    
    public static void fillList(){
        pots = new Potion[11];
        pots[0] = new Potion(3042, 3040);
        pots[1] = new Potion(139, 2434);
        pots[2] = new Potion(169, 2444);
        pots[3] = new Potion(12627, 12625);
        pots[4] = new Potion(145, 2436);
        pots[5] = new Potion(12697, 12695);
        pots[6] = new Potion(163, 2442);
        pots[7] = new Potion(3018, 3016);
        pots[8] = new Potion(3026, 3024);
        pots[9] = new Potion(157, 2440);
        pots[10] = new Potion(181, 2448);
    }
}
