package jp.ac.uryukyu.ie.e165741;

/**
 * Created by e1657 on 2017/02/01.
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class CardStock extends ArrayList<Integer>{
    private int joker_number = 0;
    public CardStock() {
        this(0);
    }
    public CardStock(int joker_number) {
        this.joker_number = joker_number;
        // ジョーカー枚数の上限を(とりあえず)９枚にしておく
        if (joker_number < 0 || joker_number > 9) {
            System.err.println("JokerCard: given number is not valid");
            System.exit(1);
        }
        clear();
        int card_number = Card.NSUITS * Card.NCARDS_PER_SUIT + joker_number;
        for (int i = 0; i < card_number; i++) {
            if (i < Card.NSUITS * Card.NCARDS_PER_SUIT) {
                add(i + 1);
            } else {
                add(0);
            }
            Collections.shuffle(this);
        }
        //　CardStockからカードを一枚引き抜く
        // CardStockの中に無ければ、null
        public Card getCardfromCardStock(){
            int lastcard = size() - 1;
            if (lastcard == -1) {
                return null;
            } else {
                int cardNum = get(lastcard);
                remove(lastcard);
                return Card.getCardfromCardCode(cardNum);
            }
        }

        //ストックをクリアし、０枚にする
        public boolean allClear() {
            clear();
            if (size() == 0) {
                return true;
            } else {
                return false;
            }
        }

        //カードをシャッフルする
        public void cardShuffle() {
            Collections.shuffle(this);
        }

        //Stockの残り枚数
        public int restNumber() {
            return this.size();
        }

        //指定したカードの有無の確認
        public boolean isCard(Card checkcard){
            if (indexOf(checkcard) == -1) {
                return false;
            } else {
                return true;
            }
        }
        /*
        * 指定したカードを山から除去
        * 手札を残したまま、山を再度組みなおす時に利用。
        * 新しいCardStockを用意し、手札分はこのメソッドで削除してから使う。
        */
        public boolean deletefromCardStock(Card card){
            //beforecardが手札にあるカードか確認する
            if(indexOf(card.getCardCode()) == -1){
                return false;
            } else {
                remove(indexOf(card.getCardCode()));
                return true;
            }
        }
    }
}
