package jp.ac.uryukyu.ie.e165741;

import java.util.Scanner;

/**
 * Created by e1657 on 2017/02/04.
 */
public class Mainmenu {
    public static void mainmenu(){
        int num=0;
        System.out.println("~~~~~ポーカーゲーム~~~~~");
        while(num!=1) {
            System.out.println("1,ゲーム\n2,役の説明\n3,ゲーム終了");
            System.out.println("選びたい項目の番号を入力してください。\n");
            Scanner scan = new Scanner(System.in);
            num = Integer.parseInt(scan.next());
            if (num == 1) {
                game.gamesistem();
            } else if (num == 2) {
                help.yaku();
            } else if (num == 3) {
                System.exit(0);
            }
        }
    }
}
