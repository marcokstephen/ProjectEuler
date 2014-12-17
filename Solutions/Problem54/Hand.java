import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> hand;
    private List<Card.Suit> suitList;
    private List<Integer> valueList;

    public List<Card> getHand() {
        return hand;
    }

    public List<Card.Suit> getSuitList(){
        return suitList;
    }

    public List<Integer> getValueList(){
        return valueList;
    }

    Hand(String c1, String c2, String c3, String c4, String c5){
        hand = new ArrayList<Card>();
        suitList = new ArrayList<Card.Suit>();
        valueList = new ArrayList<Integer>();

        hand.add(new Card(c1));
        hand.add(new Card(c2));
        hand.add(new Card(c3));
        hand.add(new Card(c4));
        hand.add(new Card(c5));

        for (int i = 0; i < hand.size(); i++){
            suitList.add(hand.get(i).getSuit());
            valueList.add(hand.get(i).getValue());
        }
    }

    public int getLowestValue(){
        int lowestValue = 14;
        for (int i = 0; i < valueList.size(); i++){
            if (valueList.get(i) < lowestValue) lowestValue = valueList.get(i);
        }
        return lowestValue;
    }
}