public class Card {

    public enum Suit {
        HEART,CLUB,SPADE,DIAMOND
    }

    private int value;
    private Suit suit;

    public Card(String cardString){
        char valueChar = cardString.charAt(0);
        char suitChar = cardString.charAt(1);

        switch (valueChar){
            case ('T'):
                value = 10;
                break;
            case ('J'):
                value = 11;
                break;
            case ('Q'):
                value = 12;
                break;
            case ('K'):
                value = 13;
                break;
            case ('A'):
                value = 14;
                break;
            default:
                value = Character.getNumericValue(valueChar);
                break;
        }
        switch (suitChar){
            case ('H'):
                suit = Suit.HEART;
                break;
            case ('C'):
                suit = Suit.CLUB;
                break;
            case ('S'):
                suit = Suit.SPADE;
                break;
            default:
                suit = Suit.DIAMOND;
                break;
        }
    }

    public Suit getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }
}