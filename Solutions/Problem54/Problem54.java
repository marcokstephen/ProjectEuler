import java.util.Collections;
import java.util.List;

public class Problem54 {

    public static void main(String[] args){
        /*BufferedReader br = new BufferedReader(new FileReader("C:/p054_poker.txt"));
        String line;
        while ((line = br.readLine()) != null){
            String cards[] = line.split(" ");

        }
        br.close();*/

        String line = "TC AC JC 9C QC 7D 2S 5D 3S AC";
        String cards[] = line.split(" ");
        Hand player1 = new Hand(cards[0],cards[1],cards[2],cards[3],cards[4]);
        Hand player2 = new Hand(cards[5],cards[6],cards[7],cards[8],cards[9]);

        System.out.println(isStraightFlush(player1));
        System.out.println(isRoyalFlush(player2));
    }

    public static boolean isRoyalFlush(Hand h){
        List<Integer> valueList = h.getValueList();
        List<Card.Suit> suitList = h.getSuitList();
        if (valueList.contains(14) && valueList.contains(13) &&
                valueList.contains(12) && valueList.contains(11) && valueList.contains(10)){
            Card.Suit suit = suitList.get(0);
            if (Collections.frequency(suitList,suit) == 5){
                return true;
            }
        }
        return false;
    }

    public static boolean isStraightFlush(Hand h){
        List<Integer> valueList = h.getValueList();
        List<Card.Suit> suitList = h.getSuitList();
        int lowestValue = h.getLowestValue();

        if (valueList.contains(lowestValue+1) && valueList.contains(lowestValue+2) && valueList.contains(lowestValue+3) && valueList.contains(lowestValue+4)){
            Card.Suit suit = suitList.get(0);
            if (Collections.frequency(suitList,suit) == 5){
                return true;
            }
        }
        return false;
    }

    public static boolean isFourOfKind(Hand h){
        List<Integer> valueList = h.getValueList();

        int value = valueList.get(0);
        if (Collections.frequency(valueList,value) == 4) return true;
        value = valueList.get(1);
        if (Collections.frequency(valueList,value) == 4) return true;
        return false;
    }

    public static boolean isFullHouse(Hand h){
        //TODO:
        return false;
    }

    public static boolean isFlush(Hand h){
        List<Card.Suit> suitList = h.getSuitList();
        Card.Suit suit = suitList.get(0);
        if (Collections.frequency(suitList,suit) == 5){
            return true;
        }
        return false;
    }

    public static boolean isStraight(Hand h){
        List<Integer> valueList = h.getValueList();
        int lowestValue = h.getLowestValue();

        if (valueList.contains(lowestValue+1) && valueList.contains(lowestValue+2) &&
                valueList.contains(lowestValue+3) && valueList.contains(lowestValue+4)) {
            return true;
        }
        return false;
    }

    public static boolean isThreeKind(Hand h){
        //TODO:
        return false;
    }

    public static boolean isTwoPairs(Hand h){
        //TODO:
        return false;
    }

    public static boolean isOnePair(Hand h){
        //TODO:
        return false;
    }

    public static int isHighCard(Hand h){
        int highestValue = 0;
        List<Integer> valueList = h.getValueList();
        for (int i = 0; i < valueList.size(); i++){
            if (valueList.get(i) > highestValue) highestValue = valueList.get(i);
        }
        return highestValue;
    }
}
