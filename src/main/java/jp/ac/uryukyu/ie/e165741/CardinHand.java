package jp.ac.uryukyu.ie.e165741;

/**
 * Created by e1657 on 2017/02/01.
 */
import java.util.ArrayList;
import java.util.Collections;

public class CardinHand extends ArrayList<Card>{
    private static final int INIT_HAND_NUMBER = 5;

    // 役マスターポーカー用
    enum HAND_CARDS {
        HIGH_CARDS(0, "ハイカード", 0),
        ONE_PAIR(1, "ワンペア", 200),
        TWO_PAIR(2,"ツーペア", 400),
        THREE_CARDS(3, "スリーカード", 800),
        STRAIGHT(4,"ストレート", 1000),
        FLUSH(5, "フラッシュ", 1400),
        FULL_HOUSE(6, "フルハウス",1800),
        FOUR_CARDS(7, "フォーカード", 2000),
        STRAIGHT_FLUSH(8,"ストーレートフラッシュ", 2400),
        FIVE_CARDS(10, "ファイブカード", 3000);

        private int Num;
        private String Name;
        private int Points;

        HAND_CARDS(int Num, String Nam, int Points) {
            this.Num = Num;
            this.Name = Nam;
            this.Points = Points;
        }

        public int getNum() {
            return Num;
        }

        public String getName() {
            return Name;
        }

        public int getPoints() {
            return Points;
        }

        final static public HAND_CARDS getHANDCARDSfromNum(int num) {
            HAND_CARDS handcard = null;
            switch (num) {
                case 0:
                    handcard = valueOf("HIGH_CARDS");
                    break;
                case 1:
                    handcard = valueOf("ONE_PAIR");
                    break;
                case 2:
                    handcard = valueOf("TWO_PAIR");
                    break;
                case 3:
                    handcard = valueOf("THREE_CARDS");
                    break;
                case 4:
                    handcard = valueOf("STRAIGHT");
                    break;
                case 5:
                    handcard = valueOf("FLUSH");
                    break;
                case 6:
                    handcard = valueOf("FULL_HOUSE");
                    break;
                case 7:
                    handcard = valueOf("FOUR_CARDS");
                    break;
                case 8:
                    handcard = valueOf("STRAIGHT_FLUSH");
                    break;
                case 9:
                    handcard = valueOf("LOYAL_STRAIGHT_FLUSH");
                    break;
                case 10:
                    handcard = valueOf("FIVE_CARDS");
                    break;
                default:
                    System.err.println("Card: given handcard is not valid");
                    System.exit(1);
                    break;
            }
            return handcard;
        };
    };

    CardinHand(){
        super();
    }
    CardinHand(CardStock cardstock) {
        super();
        clear();
        for (int i = 0; i < INIT_HAND_NUMBER; i++) {
            add(cardstock.getCardfromCardStock());
        }
        Collections.sort(this, new CardComparator());
    }

    // inHandの枚数
    public int inhandNumber() {
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


    // 手札を交換する(枚数は変わらない)
    public boolean changeCard(Card beforecard, Card aftercard) {
        // beforecardが手札にあるカードか確認する
        if (indexOf(beforecard) == -1) {
            return false;
        } else {
            set(indexOf(beforecard), aftercard);
            Collections.sort(this, new CardComparator());
            return true;
        }
    }

    // 手札を加える（１枚増える）
    public boolean addCard(Card addcard) {
        add(addcard);
        Collections.sort(this, new CardComparator());
        return true;
    }

    // 手札を削除する（１枚減る）
    public boolean deleteCard(Card deletecard) {
        // beforecardが手札にあるカードか確認する
        if (indexOf(deletecard) == -1) {
            return false;
        } else {
            remove(indexOf(deletecard));
            Collections.sort(this, new CardComparator());
            return true;
        }
    }

    // ポーカーの役チェックメソッド（通常カード５２枚に関して重複は無いものとする）
    public HAND_CARDS checkPokerState() {

        HAND_CARDS state = HAND_CARDS.HIGH_CARDS;
        int checkcard_num = INIT_HAND_NUMBER;

        ArrayList<Card> checkcard = new ArrayList<Card>();
        int jokercount = 0;

        // joker 以外の組み合わせ確認

        for (int i = 0; i < INIT_HAND_NUMBER; i++) {
            if (get(i).getCardCode() == 0) {
                ++jokercount;
            } else {
                checkcard.add(get(i));
            }
        }

        //ジョーカー５枚ならファイブカードで終了
        if(jokercount == 5 || jokercount == 4){
            state = HAND_CARDS.FIVE_CARDS;
            return state;
        }

        // ジョーカーを除いたペアの有無を探す
        checkcard_num = checkcard.size();
        int number = 0;
        int next_number = 0;
        int count = 0;

        // 小さい値からソートされている、手札が５枚前提で、このチェックが利用可能
        for (int i = 0; i < checkcard_num; i++) {

            next_number = checkcard.get(i).getNumber();

            if (state.equals(HAND_CARDS.HIGH_CARDS) && number == next_number) {
                state = HAND_CARDS.ONE_PAIR;
                count = 1;
            } else if (state.equals(HAND_CARDS.ONE_PAIR)
                    && number == next_number && count == 1) {
                state = HAND_CARDS.THREE_CARDS;
                count = 2;
            } else if (state.equals(HAND_CARDS.THREE_CARDS)
                    && number == next_number && count == 2) {
                state = HAND_CARDS.FOUR_CARDS;
                count = 0;
            } else if (state.equals(HAND_CARDS.ONE_PAIR)
                    && number == next_number && count == 0) {
                state = HAND_CARDS.TWO_PAIR;
                count = 1;
            } else if (state.equals(HAND_CARDS.THREE_CARDS)
                    && number == next_number && count == 0) {
                state = HAND_CARDS.FULL_HOUSE;
                count = 0;
            } else if (state.equals(HAND_CARDS.TWO_PAIR)
                    && number == next_number && count == 1) {
                state = HAND_CARDS.FULL_HOUSE;
                count = 0;
            } else {
                count = 0;
            }
            number = next_number;
        }

        // ワンペア＋ジョーカー１枚なら、スリーカード。ジョーカー２枚なら、フォーカード、ジョーカー３枚ならファイブカード
        if (state.equals(HAND_CARDS.ONE_PAIR) && jokercount == 1) {
            state = HAND_CARDS.THREE_CARDS;
        } else if (state.equals(HAND_CARDS.ONE_PAIR) && jokercount == 2) {
            state = HAND_CARDS.FOUR_CARDS;
        } else if (state.equals(HAND_CARDS.ONE_PAIR) && jokercount == 3) {
            state = HAND_CARDS.FIVE_CARDS;
        }
        // スリーカード＋ジョーカー１枚ならフォーカード、ジョーカー２枚ならファイブカード

        if (state.equals(HAND_CARDS.THREE_CARDS) && jokercount == 1) {
            state = HAND_CARDS.FOUR_CARDS;
        } else if (state.equals(HAND_CARDS.THREE_CARDS) && jokercount == 2) {
            state = HAND_CARDS.FIVE_CARDS;
        }

        // ツーペア＋ジョーカー１枚ならフルハウス
        if (state.equals(HAND_CARDS.TWO_PAIR) && jokercount == 1) {
            state = HAND_CARDS.FULL_HOUSE;
        }

        // ペアがあればストレート、フラッシュにはなり得ない。
        if (state.equals(HAND_CARDS.HIGH_CARDS)) {
            // ペアが無いもので、５枚のカードなら、最大と最小の差は４のはず
            // ジョーカー１枚存在し、４枚の最大と最小の差が４以内ならストレートにできる。
            // ４枚のマークがそろうならストレートフラッシュ
            // ジョーカーが２枚存在し、３枚の最大と最小の差が４以内ならストレートにできる。
            // ３枚のマークがそろうならストレートフラッシュ
            if (checkcard_num == 5 || checkcard_num == 4 || checkcard_num == 3) {
                if ((checkcard.get(0)).getNumber() == 1) {
                    if ((checkcard.get(checkcard_num - 1)).getNumber()
                            - (checkcard.get(0)).getNumber() <= 4) {
                        state = HAND_CARDS.STRAIGHT;
                    } else if (14 - (checkcard.get(1)).getNumber() <= 4) {
                        state = HAND_CARDS.STRAIGHT;
                    }
                } else {
                    if ((checkcard.get(checkcard_num - 1)).getNumber()
                            - (checkcard.get(0)).getNumber() <= 4) {
                        state = HAND_CARDS.STRAIGHT;
                    }
                }
                if (state.equals(HAND_CARDS.STRAIGHT)) {
                    for (int i = 0; i < checkcard_num; i++) {
                        if ((checkcard.get(0)).getSuit().getName()
                                .equals((checkcard.get(i)).getSuit().getName())) {
                            state = HAND_CARDS.STRAIGHT_FLUSH;
                        } else {
                            state = HAND_CARDS.STRAIGHT;
                            break;
                        }
                    }
                }
            } else if (checkcard_num == 2) {
                // ジョーカーが３枚存在し、２枚の最大と最小の差が４以内ならストレートにできるが、
                // ２枚のマークが同じならストレートフラッシュ。異なればフォーカードに。
                if ((checkcard.get(0)).getSuit().getName()
                        .equals((checkcard.get(1)).getSuit().getName())) {
                    if ((checkcard.get(checkcard_num - 1)).getNumber()
                            - (checkcard.get(0)).getNumber() <= 4) {
                        state = HAND_CARDS.STRAIGHT_FLUSH;
                    } else {
                        state = HAND_CARDS.FOUR_CARDS;
                    }
                } else {
                    state = HAND_CARDS.FOUR_CARDS;
                }

            }

            //この時点でもハイカードの時のみフラッシュのチェック
            if (state.equals(HAND_CARDS.HIGH_CARDS)) {
                for (int i = 0; i < checkcard_num; i++) {
                    if ((checkcard.get(0)).getSuit().getName()
                            .equals((checkcard.get(i)).getSuit().getName())) {
                        state = HAND_CARDS.FLUSH;
                    } else {
                        state = HAND_CARDS.HIGH_CARDS;
                        break;
                    }
                }
            }
        }
}
