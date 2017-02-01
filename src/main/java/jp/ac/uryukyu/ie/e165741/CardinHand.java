package jp.ac.uryukyu.ie.e165741;

/**
 * Created by e1657 on 2017/02/01.
 */
import java.util.Random;
import java.util.HashMap;
import java.util.ArrayList;
public class CardinHand {
    private static HashMap<Integer, String> Suit;
    private static ArrayList<Integer> number;
    private static ArrayList<Integer> Handsuit;
    private static ArrayList<Integer> Handnumber;
    public static void firstcardopen(){
        int i =1;
        int x =1;
        Cardstack cardstack = new Cardstack();
        cardstack.firstcardstack();
        Handnumber =new ArrayList<Integer>();
        Handsuit =new ArrayList<Integer>();
        Handnumber.add(0);
        Handsuit.add(0);
        Suit = new HashMap<Integer, String>();
        number = new ArrayList<Integer>();
        Suit = Cardstack.getSuit();
        number = Cardstack.getList();
        Random rn=new Random();
        Random om=new Random();
        while (i<6) {
            int rnd = rn.nextInt(3);
            int dom = om.nextInt(52);
            while(x<i){
                if(Handnumber.get(x)==dom && Handsuit.get(x)==rnd){
                    while(dom==Handnumber.get(x)||rnd==Handsuit.get(x)){
                        rnd = rn.nextInt(3);
                        dom = om.nextInt(53);
                    }
                    x=1;
                }else{
                    x++;
                }
            }
            if(dom==53){
                rnd=4;
            }
            Handnumber.add(dom);
            Handsuit.add(rnd);
            System.out.printf(i + "[%s%s]",Suit.get(rnd),number.get(dom) );
            i++;
        }
    }
}
