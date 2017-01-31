package jp.ac.uryukyu.ie.e165741;

import java.util.Scanner;

/**
 * Created by e1657 on 2017/01/31.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("~~~~~ポーカーゲーム~~~~~");
        System.out.println("1,ゲームスタート\n2,役の説明\n3,ゲーム終了");
        System.out.println("選びたい項目の番号を入力してください。");
        Scanner scan = new Scanner(System.in);
        int num =  Integer.parseInt(scan.next());
        if(num==1){
        }
        else if(num==2){
        }
        else if (num==3){
            System.exit(0);
        }
        else{
            System.out.println("項目の番号を入力してください");
        }
    }
}
