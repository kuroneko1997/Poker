package jp.ac.uryukyu.ie.e165741;
/**
 * Created by e1657 on 2017/02/03.
 */
import java.util.ArrayList;
import java.util.Collections;

public class Judgecard {
    private static ArrayList<Integer> judgeSuit;
    private static ArrayList<Integer>judgenumber;
    private static ArrayList<Integer> number;
    private static ArrayList<Integer>judgeflash;
    public static void judgecard(int c) {
        int a = 1;
        int b = 1;
        int x =0;
        int Scount = 0;
        int jcount = 0;
        int pcount =0;
        int pcount2=0;
        int pcount3=0;
        int flash =0;
        judgeSuit = new ArrayList<Integer>();
        judgenumber = new ArrayList<Integer>();
        number = new ArrayList<Integer>();
        number = Cardstack.getList();
        if (c == 1) {
            judgeSuit = CardinHand.getHandsuit();
            judgenumber = CardinHand.getHandnumber();
        } else {
            judgeSuit = CardinHand.getHandsuit2();
            judgenumber = CardinHand.getHandnumber2();
        }
        judgeflash= new ArrayList<Integer>();
        while(b<6){
            judgeflash.add(number.get(judgenumber.get(b)));
            b++;
        }
        Collections.sort(judgeflash);
        if (judgeSuit.get(a) == 4) {
            jcount++;
            a = 3;
            while (a < judgeSuit.size()) {
                if (judgeSuit.get(2) == judgeSuit.get(a)) {
                    Scount++;
                }
                a++;
            }

        }else{
            while (a < 5) {
                a++;
                if (judgeSuit.get(1) == judgeSuit.get(a)) {
                        Scount++;
                }
            }
        }
        if(jcount==1&&Scount==3){
            flash=Judgeflash(0);
        }
        else if(Scount==4){
            flash=Judgeflash(1);

        }
        else {
            if (jcount == 1) {
                if (judgeflash.get(1) == judgeflash.get(4)) {
                    pcount = 4;
                } else {
                    if (judgeflash.get(1) == judgeflash.get(3) || judgeflash.get(2) == judgeflash.get(4)) {
                        pcount = 3;
                    } else if (judgeflash.get(3) == judgeflash.get(4)) {
                        pcount++;
                    } else if (judgeflash.get(1) == judgeflash.get(2)) {
                        pcount++;
                    }
                }
                if (pcount==0){
                    flash=Judgeflash(0);
                }
            } else {
                  if (judgeflash.get(0) == judgeflash.get(3)||judgeflash.get(1) == judgeflash.get(4) ) {
                      pcount=4;
                  }else{
                      while (x < 3) {
                          if (judgeflash.get(x) == judgeflash.get(x + 2)) {
                              pcount=3;
                              if (x==0){if (judgeflash.get(3) == judgeflash.get(4)){pcount2++;}}
                              else if(x==2) {if (judgeflash.get(0) == judgeflash.get(1)) {pcount2++;}}
                          }else{
                              if(x<=2){
                                  if(judgeflash.get(x) == judgeflash.get(x+1)){
                                      pcount3++;
                                  }
                              }
                              else {
                                  if (judgeflash.get(3) == judgeflash.get(4)) {
                                      pcount3++;
                                  } 

                              }
                          }
                          x++;
                      }

                  }
                  if(pcount==0&&pcount2==0&&pcount3==0){flash=Judgeflash(1);}
            }
        }
        if (jcount==1){
            if (Scount==3){
                if(flash==3){
                    System.out.println("ストレートフラッシュ");
                    result.magnification(20);
                }else{
                    System.out.println("フラッシュ");
                    result.magnification(5);
                }
            }else{
                if(pcount==4){
                    System.out.println("ファイブカード");
                    result.magnification(15);
                }else if(pcount==3) {
                    System.out.println("フォーカード");
                    result.magnification(10);
                }else if(pcount==2) {
                    System.out.println("フルハウス");
                    result.magnification(8);
                }else if(pcount==1){
                    System.out.println("スリーカード");
                    result.magnification(4);
                }else{
                    System.out.println("ワンペア");
                    result.magnification(2);
                }
            }
        }else{
            if (Scount==4){
                if(flash==4){
                    System.out.println("ストレートフラッシュ");
                    result.magnification(20);
                }else{
                    System.out.println("フラッシュ");
                    result.magnification(5);
                }
            }else {
                if (pcount == 4) {
                    System.out.println("フォーカード");
                    result.magnification(10);
                } else if (pcount == 3) {
                    System.out.println("スリーカード");
                    result.magnification(4);
                } else if (pcount + pcount2 == 4) {
                    System.out.println("フルハウス");
                    result.magnification(8);
                } else if (pcount == 0 && pcount3 == 2) {
                    System.out.println("ツーペア");
                    result.magnification(5);
                } else if (pcount == 0 && pcount3 == 1) {
                    System.out.println("ワンペア");
                    result.magnification(2);
                } else if (pcount == 0 && pcount2 == 0 && pcount3 == 0) {
                    if (flash == 4) {
                        System.out.println("フラッシュ");
                        result.magnification(5);
                    } else {
                        System.out.println("ハイカード");
                        result.magnification(0);
                    }
                }
            }
        }

    }
    public static int Judgeflash(int k){
        int flash =0;
        int x =0;
        if (k==1){
            if (judgeflash.get(0)==1&&Collections.max(judgeflash)==13) {
                x=1;
                flash++;
                while (x < 4) {
                    if (judgeflash.get(x) == judgeflash.get(x + 1) + 1) {
                        flash++;
                        x++;
                    }
                }
            }else{
                while (x < 4) {
                    if (judgeflash.get(x) == judgeflash.get(x + 1) - 1) {
                        flash++;

                    }
                    x++;
                }
            }
        }else{
            x=1;
            if (judgeflash.get(1)==1&&Collections.max(judgeflash)==13) {
                flash++;
                Collections.reverse(judgeflash);
                while (x < 4) {
                    if (judgeflash.get(x) == judgeflash.get(x + 1) + 1) {
                        flash++;
                        x++;
                    }
                }
            }else{
                while (x < 4) {
                    if (judgeflash.get(x) == judgeflash.get(x + 1) - 1) {
                        flash++;
                        x++;
                    }
                }
            }
        }
        return flash;
    }
}