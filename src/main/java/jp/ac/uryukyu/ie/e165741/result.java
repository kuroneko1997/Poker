package jp.ac.uryukyu.ie.e165741;

/**
 * Created by e1657 on 2017/02/04.
 */
public class result {
    private static int mymoney=50000;
    private static int betmoney=0;
    private static int mnumber=0;
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
    public static void magnification(int s){
        mnumber=s;
    }
    public static void result(){
            mymoney=mymoney+betmoney*mnumber;
    }
    public static int getmymoney(){
        return mymoney;
    }
}
