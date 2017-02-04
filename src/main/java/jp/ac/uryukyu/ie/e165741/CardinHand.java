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
    private static ArrayList<Integer> Handsuit2;
    private static ArrayList<Integer> Handnumber2;
    private static ArrayList changecardnumber ;
    private static Random rn=new Random();
    private static Random om=new Random();
    public static void firstcardopen(){
        int i =1;
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
        while (i<6) {
            int x =1;
            int rnd = rn.nextInt(3);
            int dom = om.nextInt(52);
            while(x<Handnumber.size()){
                if(number.get(Handnumber.get(x)) == number.get(dom) && Suit.get(Handsuit.get(x)) == Suit.get(rnd)){
                    while(number.get(Handnumber.get(x)) == number.get(dom) || Suit.get(Handsuit.get(x)) == Suit.get(rnd)){
                        rnd = rn.nextInt(3);
                        dom = om.nextInt(52);
                    }
                    x=1;
                }else{
                    x++;
                }
            }
            if(dom==52){
                rnd=4;
            }
            Handnumber.add(dom);
            Handsuit.add(rnd);
            System.out.printf(i+ "[%s%s]",Suit.get(rnd),number.get(dom) );
            i++;
        }
    }
    public static void secandcardopen(int k){
        int i=1;
        int v=0;
        int j=1;
        Handnumber2 =new ArrayList<Integer>();
        Handsuit2 =new ArrayList<Integer>();
        Handnumber2.add(0);
        Handsuit2.add(0);
        while(i<6) {
            Handnumber2.add(Handnumber.get(i));
            Handsuit2.add(Handsuit.get(i));
            i++;
        }
        changecardnumber =new ArrayList<Integer>();
        changecardnumber =game.getchengenumber();
        while (v<k){
            int x=0;
            int rnd = rn.nextInt(3);
            int dom = om.nextInt(52);
            while(x<Handsuit.size()) {
                if (number.get(Handnumber.get(x)) == number.get(dom) && Suit.get(Handsuit.get(x)) == Suit.get(rnd)) {
                    while (number.get(Handnumber.get(x)) == number.get(dom) || Suit.get(Handsuit.get(x)) == Suit.get(rnd)) {
                        rnd = rn.nextInt(3);
                        dom = om.nextInt(52);
                    }
                    x = 0;
                } else {
                    x++;
                }
            }if (dom == 52) {
                rnd = 4;
            }
            Handnumber.add(dom);
            Handsuit.add(rnd);
            Handnumber2.set((Integer) changecardnumber.get(v),dom);
            Handsuit2.set((Integer)changecardnumber.get(v),rnd);
            v++;
        }
        while(j<6) {
            System.out.printf(j + "[%s%s]", Suit.get(Handsuit2.get(j)), number.get(Handnumber2.get(j)));
            j++;
        }
    }
    public static ArrayList getHandnumber(){return Handnumber;}
    public static ArrayList getHandnumber2(){return Handnumber2;}
    public static ArrayList getHandsuit(){return Handsuit;}
    public static ArrayList getHandsuit2(){return Handsuit2;}
}
