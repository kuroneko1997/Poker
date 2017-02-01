package jp.ac.uryukyu.ie.e165741;

/**
 * Created by e1657 on 2017/02/01.
 */
public class Card {
    enum Suit {

        JOKER(0, "J"), SPADE(4, "♠"), HEART(3, "♥"), DIAMOND(2, "♦"), CLUB(1, "♣");

        private int Num;
        private String Name;

        Suit(int Num, String Name) {
            this.Name = Name;
            this.Num = Num;
        }

        // Suitの強弱の為の数値(4 > 3 >　2 > 1) ※0を除く
        public int getNum() {
            return Num;
        }

        // 表示用
        public String getName() {
            return Name;
        }

        // 0はJOKERと定義する
        public static Suit getSuitfromNum(int num) {
            Suit suit = null;
            switch (num) {
                case 0:
                    suit = Suit.valueOf("JOKER");
                    break;
                case 4:
                    suit = Suit.valueOf("SPADE");
                    break;
                case 3:
                    suit = Suit.valueOf("HEART");
                    break;
                case 2:
                    suit = Suit.valueOf("DIAMOND");
                    break;
                case 1:
                    suit = Suit.valueOf("CLUB");
                    break;
                default:
                    System.err.println("Card: given suit is not valid");
                    System.exit(1);
                    break;
            }
            return suit;
        };
    };

    // スーツの数
    public final static int NSUITS = 4;

    // スーツあたりのカードの数
    public final static int NCARDS_PER_SUIT = 13;

    // インスタンス変数
    private Suit suit; // スーツ(HEART("♥"),DIAMOND("♦"),SPADE("♠"),CLUB("♣"))
    private int number; // 番号(1-13)とジョーカーの0

    // コンストラクタ
    public Card(Suit suit, int number) {
        if (suit != Suit.JOKER && suit != Suit.HEART && suit != Suit.DIAMOND
                && suit != Suit.SPADE && suit != Suit.CLUB) {
            System.err.println("Card: given suit is not valid");
            System.exit(1);
        }

        if ((number < 0 || NCARDS_PER_SUIT < number)) {
            if (!(number == 0 && suit == Suit.JOKER)) {
                System.err.println("Card: given number is not valid");
                System.exit(1);
            }
        }
        this.number = number;
        this.suit = suit;
    }

    // このカードの数を取得する　※0は、Joker
    public int getNumber() {
        return number;
    }

    /*
     * 全てのカードを連番（CardCode）で定義し管理。 Joker = 0;
     * ♣1= 1,♦1= 2,♥1= 3,♠1= 4
     * ♣2= 5,♦2= 6,♥2= 7,♠2= 8
     * ♣3= 9,♦3=10,♥3=11,♠3=12
     *  ・・・・・・・・・
     *  こうすることで、Jokerを除いて♠Kが一番数が大きく、♣1= 1が一番小さい数になり、
     *  通常のカードの優劣はコードの数値の大きさで判別可能になる。
     */

    public int getCardCode() {
        int n = -1;
        if (number == 0 && suit.getNum() == 0) {
            n = 0;
        } else {
            n = (number - 1) * NSUITS + suit.getNum();
        }
        return n;
    }

    // このカードの種類を取得する
    public Suit getSuit() {
        return suit;
    }
}
