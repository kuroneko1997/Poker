package jp.ac.uryukyu.ie.e165741;

import java.util.Scanner;

/**
 * Created by e1657 on 2017/01/31.
 */
public class game {
    private static int mymoney=50000;
    private static int num = 0;
    private static int num2 = 6;
    private static int num3 = 0;
    public static void gamesistem(){
        while(num!=1&&num!=2&&num!=3) {
            System.out.println("掛け金を決めてください。");
            System.out.println("1.100\n2.1000\n3.10000\n");
            System.out.printf("現在の所持金%d\n", mymoney);
            System.out.println("選びたい項目の番号を入力してください。\n");
            Scanner scan = new Scanner(System.in);
            num = Integer.parseInt(scan.next());
        }
        System.out.println("ゲームスタート");
        System.out.println("最初の手札");
        CardinHand.firstcardopen();
        while(num2>=6) {
            System.out.println("交換したいカードの枚数を入力してください。");
            Scanner scan = new Scanner(System.in);
            num2 = Integer.parseInt(scan.next());
        }
    }
}
