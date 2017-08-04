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
public class RSSets {
    static RSItemSet[] theSets;
    static RSItemSet unholybook;
    static RSItemSet dharok;
    static RSItemSet lawbook;
    static RSItemSet rune;
    

    
    public static void fillsets(){
        theSets = new RSItemSet[12];
        theSets[0] = newSet(3831,3832,3833,3834, 13151, "unholy book");
        theSets[1] = newSet(4716, 4718, 4720, 4722, 12877, "Dharok's");  
        theSets[2] = newSet(12617, 12618, 12619, 12620, 13157, "Book of law");   
        theSets[3] = newSet(1079, 1127, 1201, 1163, 13024, "Rune armour");  
        theSets[4] = newSet(4728,4724,4726,4730,12873,"guth");
        theSets[5] = newSet(4753,4755,4757,4759,12875,"verac");
        theSets[6] = newSet(3486,3483,3481,3488,13036,"gilded");
        theSets[7] = newSet(4745,4747,4749,4751,12879,"torag");
        theSets[8] = newSet(12235,12237,12241,12243,12980,"iron (g)");
        theSets[9] = newSet(10370,10372,10374,10368,13161,"zam d'hide");
        theSets[10] = newSet(4732,4734,4736,4738,12883,"karils");
        theSets[11] = newSet(4708,4710,4712,4714,12881,"ahrim");
    }
    
    public static String[] names(){
        String[] res = new String[theSets.length];
        for (int i = 0; i < theSets.length; i++) {
            res[i] = theSets[i].setName;
        }
        return res;
    }
    
    public static void print(){
        unholybook.print();
        dharok.print();
        lawbook.print();
        rune.print();
    }
        
    private static RSItemSet newSet(int id1, int id2, int id3, int id4, int setid, String name){
        int[] ids = {id1, id2, id3, id4};
        return new RSItemSet(ids,setid, name);
    }
    
    public static RSItemSet getByName(String name){
        for (RSItemSet theSet : theSets) {
            if (theSet.setName.equals(name)) {
                System.out.println(theSet.setName + " = " + name);
                return theSet;
            }
        }
        return null;
    }
}
