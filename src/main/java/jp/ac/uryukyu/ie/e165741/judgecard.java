package jp.ac.uryukyu.ie.e165741;
/**
 * Created by e1657 on 2017/02/03.
 */
import java.util.ArrayList;
import java.util.Collections;

public class Judgecard {
    private static ArrayList<Integer> judgeSuit;
    private static ArrayList<Integer> judgenumber;
    private static ArrayList<Integer> number;
    private static ArrayList<Integer> judgestraight;

    public static void judgecard(int c) {
        int a = 1;
        int b = 1;
        int Scount = 0;
        int jcount = 0;
        int pcount = 0;
        int pcount2 = 0;
        int pcount3 = 0;
        int straight = 0;
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
        judgestraight = new ArrayList<Integer>();
        while (b < 6) {
            judgestraight.add(number.get(judgenumber.get(b)));
            b++;
        }
        Collections.sort(judgestraight);
        if (judgeSuit.get(a) == 4) {
            jcount++;
        }
        if (jcount == 1) {
            pcount = JudgePair();
        } else {
            pcount = JudgePair2(0);
            pcount2 = JudgePair2(1);
            pcount3 = JudgePair2(2);
        }
        Scount = Judgeflash(jcount);
        if (jcount == 1 && Scount == 3 || jcount == 1 && pcount == 0) {
            straight = Judgestraight(0);
        } else if (Scount == 4 || pcount == 0 && pcount2 == 0 && pcount3 == 0) {
            straight = Judgestraight(1);
        }
        Judgeyaku(jcount, Scount, straight, pcount, pcount2, pcount3);
    }

    public static void Judgeyaku(int jcount, int Scount, int straight, int pcount, int pcount2, int pcount3) {
        if (jcount == 1 && Scount == 3 && straight == 3 || Scount == 4 && straight == 4) {
            System.out.println("ストレートフラッシュ");
            result.magnification(20);
        } else if (jcount == 1 && pcount == 4) {
            System.out.println("ファイブカード");
            result.magnification(15);
        } else if (jcount == 1 && pcount == 3 || pcount == 4) {
            System.out.println("フォーカード");
            result.magnification(10);
        } else if (jcount == 1 && pcount == 2 || pcount + pcount2 == 4) {
            System.out.println("フルハウス");
            result.magnification(8);
        } else if (Scount != 3 && straight == 3 && jcount == 1 || Scount != 4 && straight == 4) {
            System.out.println("ストレート");
            result.magnification(6);
        } else if (jcount == 1 && Scount == 3 && straight != 3 || Scount == 4 && straight != 4) {
            System.out.println("フラッシュ");
            result.magnification(5);
        } else if (jcount == 1 && pcount == 1 || jcount != 1 && pcount == 3) {
            System.out.println("スリーカード");
            result.magnification(4);
        } else if (pcount == 0 && pcount3 == 2) {
            System.out.println("ツーペア");
            result.magnification(5);
        } else if (jcount == 1 && pcount == 0 && Scount == 0 && straight == 0 || pcount == 0 && pcount3 == 1) {
            System.out.println("ワンペア");
            result.magnification(2);
        } else {
            System.out.println("ハイカード");
            result.magnification(0);
        }

    }


    public static int Judgestraight(int k) {
        int straight = 0;
        int x = 0;
        if (k == 1) {
            if (judgestraight.get(0) == 1 && Collections.max(judgestraight) == 13) {
                x = 1;
                straight++;
                while (x < 4) {
                    if (judgestraight.get(x) == judgestraight.get(x + 1) + 1) {
                        straight++;
                        x++;
                    }
                }
            } else {
                while (x < 4) {
                    if (judgestraight.get(x) == judgestraight.get(x + 1) - 1) {
                        straight++;

                    }
                    x++;
                }
            }
        } else {
            x = 1;
            if (judgestraight.get(1) == 1 && Collections.max(judgestraight) == 13) {
                straight++;
                Collections.reverse(judgestraight);
                while (x < 4) {
                    if (judgestraight.get(x) == judgestraight.get(x + 1) + 1) {
                        straight++;
                        x++;
                    }
                }
            } else {
                while (x < 4) {
                    if (judgestraight.get(x) == judgestraight.get(x + 1) - 1) {
                        straight++;
                        x++;
                    }
                }
            }
        }
        return straight;
    }

    public static int Judgeflash(int jcount) {
        int Scount = 0;
        if (jcount == 1) {
            int a = 3;
            while (a < judgeSuit.size()) {
                if (judgeSuit.get(2) == judgeSuit.get(a)) {
                    Scount++;
                }
                a++;
            }

        } else {
            int a = 1;
            while (a < 5) {
                a++;
                if (judgeSuit.get(1) == judgeSuit.get(a)) {
                    Scount++;
                }
            }
        }
        return Scount;
    }

    public static int JudgePair() {
        int pcount = 0;
        if (judgestraight.get(1) == judgestraight.get(4)) {
            pcount = 4;
        } else {
            if (judgestraight.get(1) == judgestraight.get(3) || judgestraight.get(2) == judgestraight.get(4)) {
                pcount = 3;
            } else if (judgestraight.get(3) == judgestraight.get(4)) {
                pcount++;
            } else if (judgestraight.get(1) == judgestraight.get(2)) {
                pcount++;
            }
        }
        return pcount;
    }
    public static int JudgePair2(int k) {
        int pcount=0;
        int pcount2=0;
        int pcount3=0;
        int x=0;
        if (judgestraight.get(0) == judgestraight.get(3) || judgestraight.get(1) == judgestraight.get(4)) {
            pcount = 4;
        } else {
            while (x < 3) {
                if (judgestraight.get(x) == judgestraight.get(x + 2)) {
                    pcount = 3;
                    if (x == 0) {
                        if (judgestraight.get(3) == judgestraight.get(4)) {
                            pcount2++;
                        }
                    } else if (x == 2) {
                        if (judgestraight.get(0) == judgestraight.get(1)) {
                            pcount2++;
                        }
                    }
                } else {
                    if (x < 2) {
                        if (judgestraight.get(x) == judgestraight.get(x + 1)) {
                            pcount3++;
                        }
                    } else {
                        if (judgestraight.get(3) == judgestraight.get(4)) {
                            pcount3++;
                        }else if (judgestraight.get(2) == judgestraight.get(3)){
                            pcount3++;
                        }

                    }
                }
                x++;
            }

        }
        if(k==0){
           return pcount;
        }else if(k==1){
            return pcount2;
        }else{
            return pcount3;
        }
    }
}



