package jp.ac.uryukyu.ie.e165741;

import java.util.Scanner;
import java.util.ArrayList;
/**
 * Created by e1657 on 2017/01/31.
 */
//ゲーム進行を行うクラス
public class game {
    private static int num ;
    private static int num2 ;
    private static int num3 ;
    private static ArrayList changecardnumber ;//変えるカードの番号を格納するリスト
    public static void gamesistem(){
        num=0;
        num2=6;
        while(num!=1&&num!=2&&num!=3) {
            System.out.println("掛け金を決めてください。");
            System.out.println("1.100\n2.1000\n3.10000\n");
            System.out.printf("現在の所持金%d\n", result.getmymoney());
            System.out.println("選びたい項目の番号を入力してください。\n");
            Scanner scan = new Scanner(System.in);
            num = Integer.parseInt(scan.next());
            result.bet(num);
        }
        //最初の手札
        System.out.println("~~~ゲームスタート~~~");
        System.out.println("---最初の手札---");
        CardinHand.firstcardopen();
        Judgecard.judgecard(1);
        //カード交換。なければ金額を出す
        while(num2>=6) {
            System.out.println("\n交換したいカードの枚数を入力してください。");
            Scanner scan = new Scanner(System.in);
            num2 = Integer.parseInt(scan.next());
        }
        if(num2>0){
            System.out.println("交換したいカードの番号を入力してください。");
            changecardnumber = new ArrayList<Integer>();
            int i=0;
            while(i<num2) {
                Scanner ten = new Scanner(System.in);
                changecardnumber.add(Integer.parseInt(ten.next()));
                i++;
            }
            //２回目の手札。ここで最終金額を出す。
            System.out.println("---ラストカード---");
            CardinHand.secandcardopen(num2);
            Judgecard.judgecard(0);
            result.result();
            System.out.printf("現在の所持金：%d\n",result.getmymoney());
        }else{
            result.result();
            System.out.printf("現在の所持金：%d\n",result.getmymoney());
        }
        //ゲームを続けるかどうかの問い
        System.out.println("ゲームを続けますか？（y:1/n:0）");
        Scanner scan = new Scanner(System.in);
        num3 =Integer.parseInt(scan.next());
        if(num3==1){
            game.gamesistem();
        }else{
            Mainmenu.mainmenu();
        }
    }
    //他クラス用のreturnメソッド
    public static ArrayList getchengenumber(){
        return changecardnumber;
    }
}
