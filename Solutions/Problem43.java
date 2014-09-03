import java.util.ArrayList;
import java.util.List;

public class Problem43 {
	public static void main(String[] args){
		List<String> seventeenMultiples = new ArrayList<String>();
		List<String> thirteenMultiples = new ArrayList<String>();
		List<String> firstCombinedList = new ArrayList<String>();
		List<String> secondCombinedList = new ArrayList<String>();
		List<String> thirdCombinedList = new ArrayList<String>();
		List<String> fourthCombinedList = new ArrayList<String>();
		List<String> fifthCombinedList = new ArrayList<String>();
		
		for (int i = 17; i <= 987; i+=17){
			String number = i+"";
			if (i < 100) number = "0"+number;
			
			if (number.contains("5") && number.contains("0")){
				//do nothing
			} else if (!isPandigital(number)) {
				//do nothing
			} else {
				seventeenMultiples.add(number);
			}
		}
		
		for (int i = 13; i <= 987; i+=13){
			String number = i+"";
			if (i < 100) number = "0"+number;
			if (number.contains("5") && number.contains("0")){
				//do nothing
			} else if (!isPandigital(number)){
				//do nothing
			} else {
				thirteenMultiples.add(number);
			}
		}
		
		for (int s = 0; s < seventeenMultiples.size(); s++){
			String seventeenString = seventeenMultiples.get(s).substring(0, 2);
			String thirteenString = "";
			String outputString = "";
			for (int t = 0; t < thirteenMultiples.size(); t++){
				thirteenString = thirteenMultiples.get(t).substring(1);
				if (thirteenString.equals(seventeenString)){
					outputString = thirteenMultiples.get(t) + seventeenMultiples.get(s).substring(2);
					if (isPandigital(outputString)){
						firstCombinedList.add(outputString);
					}
				}
			}
		}
		
		for (int k = 0; k < firstCombinedList.size(); k++){
			for (int i = 0; i <= 5; i+=5){
				String toTest = firstCombinedList.get(k);
				String toAppend = ""+i;
				toTest = toAppend + toTest;
				if (!isPandigital(toTest)){
					//do nothing
				} else if (Integer.parseInt(toTest.substring(0,3)) % 11 == 0) {
					secondCombinedList.add(toTest);
				} else {
					//do nothing
				}
			}
		}
		
		for (int s = 0; s < secondCombinedList.size(); s++){
			String toAppend = "";
			for (int i = 0; i < 10; i++){
				String base = secondCombinedList.get(s);
				toAppend = ""+i;
				base = toAppend+base;
				if (!isPandigital(base)){
					//do nothing
				} else if (Integer.parseInt(base.substring(0,3))%7==0){
					thirdCombinedList.add(base);
				}
			}
		}
		
		for (int s = 0; s < thirdCombinedList.size(); s++){
			String toAppend = "";
			for (int i = 0; i < 10; i+=2){
				String base = thirdCombinedList.get(s);
				toAppend = ""+i;
				base = toAppend+base;
				if (!isPandigital(base)){
					//do nothing
				} else {
					fourthCombinedList.add(base);
				}
			}
		}
		
		for (int s = 0; s < fourthCombinedList.size(); s++){
			String toAppend = "";
			for (int i = 0; i < 10; i++){
				String base = fourthCombinedList.get(s);
				toAppend = ""+i;
				base = toAppend+base;
				if (!isPandigital(base)){
					//do nothing
				} else if (Integer.parseInt(base.substring(0,3))%3==0){
					fifthCombinedList.add(base);
				}
			}
		}
		
		for (int s = 0; s < fifthCombinedList.size(); s++){
			String value = fifthCombinedList.get(s);
			System.out.print("14"+value+"+");
			System.out.print("41"+value+"+");
		}
	}
	
	public static boolean isPandigital(String number){
		int length = number.length();
		for (int i = 0; i < length-1; i++){
			char currentTest = number.charAt(i);
			for (int m = 0; m < length; m++){
				char current = number.charAt(m);
				if (current == currentTest && m != i) return false;
			}
		}
		return true;
	}
}
