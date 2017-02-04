package jp.ac.uryukyu.ie.e165741;

/**
 * Created by e1657 on 2017/01/31.
 */
//役の説明をするクラス
public class help {
    public static void yaku(){
        System.out.println("====搭載されている役一覧====");
        System.out.println("ハイカード：役ができていない状態");
        System.out.println("ワンペア：同数字が2枚ある場合。倍率2倍");
        System.out.println("ツーペア：同数字の組が２つある場合。倍率５倍");
        System.out.println("スリーカード：同数字が３枚ある場合。倍率４倍");
        System.out.println("フォーカード：同数字が4枚ある場合。倍率10倍");
        System.out.println("ファイブカード：同数字が4枚とジョーカーが手札に揃っている場合。倍率15倍");
        System.out.println("フルハウス：ワンペアとスリーカードが揃っている場合。倍率8倍");
        System.out.println("フラッシュ：スート（♦などのようなマーク）が揃っている場合。倍率５倍");
        System.out.println("ストレート：手札内で数字が連続する場合。倍率６倍");
        System.out.println("ストレートフラッシュ：スートが揃い、数字が連続している場合。倍率20倍");
        System.out.println("ジョーカーは手札に合わせて最適なスート、数字と見做してよい。");
        System.out.println("(例として[♦の9,ジョーカー,♦の8,♦のJ,♦のQ]の場合、ジョーカーは♦の10と見做してストレートフラッシュになる。）");
        System.out.println("");
        System.out.println("");
    }
}
