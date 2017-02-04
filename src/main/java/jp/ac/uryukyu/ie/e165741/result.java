package jp.ac.uryukyu.ie.e165741;

/**
 * Created by e1657 on 2017/02/04.
 */
//現在の所持金、ゲーム内での金の精算をするクラス
public class result {
    private static int mymoney=50000;//初期所持金
    private static int betmoney=0;//掛け金
    private static int mnumber=0;//役の倍率
    //掛け金の判定
    public static void bet(int k){
        if (k==1){
            betmoney=100;
        }else if(k==2){
            betmoney=1000;
        }else{
            betmoney=10000;
        }
        mymoney=mymoney-betmoney;
    }
    //役の倍率の判定
    public static void magnification(int s){
        mnumber=s;
    }
    //1ゲームでの最終金額
    public static void result(){
            mymoney=mymoney+betmoney*mnumber;
    }
    //現在の所持金の値を返す
    public static int getmymoney(){
        return mymoney;
    }
}
