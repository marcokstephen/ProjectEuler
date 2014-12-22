import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Problem98 {

    static String wordString;
    static List<String> wordStringList;
    static List<List<String>> listOfAnagramLists;
    static List<Integer> exponentList;

    public static void main(String[] args) throws IOException{

        //creating the list of words
        listOfAnagramLists = new ArrayList<List<String>>();
        BufferedReader br = new BufferedReader(new FileReader("/Users/stephen/Downloads/p098_words.txt"));
        wordString = br.readLine();
        br.close();
        wordString = wordString.replaceAll("\"", "");
        wordStringList = new LinkedList<String>(Arrays.asList(wordString.split(",")));

        //creating the list of squared numbers
        //the base case: 4 is the lowest digit that has a two digit product when squared
        exponentList = new LinkedList<Integer>();
        int base = 4;
        int product = 16;
        while (product < 987654321){
            product = base*base;
            if (validProduct(product)){
                exponentList.add(product);
            }
            base++;
        }

        while (wordStringList.size() > 0){
            List<String> anagramList = new ArrayList<String>();

            String stringA = wordStringList.remove(0);
            anagramList.add(stringA);

            for (int b = 0; b < wordStringList.size(); b++){
                String stringB = wordStringList.get(b);

                if (isAnagram(stringA, stringB)){
                    anagramList.add(stringB);
                    wordStringList.remove(b);
                    b--;
                }
            }

            //can accommodate up to 3 arrangements
            if (anagramList.size() == 2){
                listOfAnagramLists.add(anagramList);
            } else if (anagramList.size() == 3){
                List<String> tempList = new ArrayList<String>();
                tempList.add(anagramList.get(0));
                tempList.add(anagramList.get(1));
                listOfAnagramLists.add(tempList);
                tempList = new ArrayList<String>();
                tempList.add(anagramList.get(1));
                tempList.add(anagramList.get(2));
                listOfAnagramLists.add(tempList);
                tempList = new ArrayList<String>();
                tempList.add(anagramList.get(0));
                tempList.add(anagramList.get(2));
                listOfAnagramLists.add(tempList);
            }
        }

        for (int i = 0; i < listOfAnagramLists.size(); i++){
            List<String> anagramList = listOfAnagramLists.get(i);
            //removing duplicate letters from words by replacing them with non-duplicate values.
            //this allows for use of letters as keys in HashMap
            String stringA = anagramList.get(0);
            for (int k = 0; k < stringA.length(); k++){
                char c = stringA.charAt(k);
                int numOccurrences = stringA.length() - stringA.replace(c+"", "").length();
                if (numOccurrences == 2){
                    listOfAnagramLists.remove(i);

                    stringA = stringA.replaceFirst(c+"","1");
                    String stringB = anagramList.get(1);
                    stringB = stringB.replaceFirst(c+"","1");
                    anagramList = new ArrayList<String>();
                    anagramList.add(stringA);
                    anagramList.add(stringB);
                    listOfAnagramLists.add(anagramList);

                    stringB = stringB.replaceAll(c+"","1");
                    stringB = stringB.replaceFirst("1",c+"");
                    anagramList = new ArrayList<String>();
                    anagramList.add(stringA);
                    anagramList.add(stringB);
                    listOfAnagramLists.add(anagramList);
                }
            }
        }

        int maxAnswer = 0;
        for (int i = 0; i < listOfAnagramLists.size(); i++){
            List<String> anagramList = listOfAnagramLists.get(i);
            String a = anagramList.get(0);
            String b = anagramList.get(1);

            for (int j = 0; j < exponentList.size(); j++){
                int num = exponentList.get(j);
                if ((num+"").length() == a.length()) {
                    int remappedNumber = remapWordToNumber(a, b, exponentList.get(j));
                    if ((Math.pow((int)Math.sqrt(remappedNumber),2) == remappedNumber) &&
                            ((num+"").length() == (remappedNumber+"").length())) {
                        System.out.println(a +", " + b + " -- " + exponentList.get(j) + ", " + remappedNumber);
                        if (remappedNumber > maxAnswer) maxAnswer = remappedNumber;
                        if (num > maxAnswer) maxAnswer = num;
                    }
                }
            }
        }
        System.out.println(maxAnswer);
    }

    public static boolean isAnagram(String a, String b){
        if (a.length() == b.length()){
            for (int i = 0; i < a.length(); i++){
                int countA = a.length() - a.replace(a.charAt(i)+"", "").length();
                int countB = b.length() - b.replace(a.charAt(i)+"", "").length();

                if (countA != countB) return false;
            }
            return true;
        }
        return false;
    }

    public static int remapWordToNumber(String a, String b, int number){
        //currently remapping word to number to return the number mapping for String b
        Map<Character,Integer> myMap = new HashMap<Character, Integer>();
        String numberAsString = number+"";
        for (int i = 0; i < a.length(); i++){

            //gets the first digit of number
            int firstNum = Integer.parseInt(numberAsString.substring(0, 1));
            numberAsString = numberAsString.substring(1);

            //places the character and number into the map
            myMap.put(a.charAt(i), firstNum);
        }

        int output = 0;
        for (int i = 0; i < b.length(); i++){
            output *= 10;
            output += myMap.get(b.charAt(i));
        }

        return output;
    }

    public static boolean validProduct(int product){
        //ensure that the product is valid (no repeating digits)
        String productString = product+"";
        for (int i = 0; i < productString.length(); i++){
            char c = productString.charAt(i);
            for (int j = i+1; j < productString.length(); j++){
                char d = productString.charAt(j);
                if (c == d) return false;
            }
        }
        return true;
    }
}

