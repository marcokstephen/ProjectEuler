import java.util.HashMap;
import java.util.Iterator;

public class Problem12 {
	static int currentSeqNum = 1;
	
	public static void main(String[] args){
		int factorCount = 0;
		int triValue = 0;
		do{
			triValue += currentSeqNum;
			factorCount = countFactors2(triValue);
			currentSeqNum++;
		} while(factorCount <= 500);
		System.out.println(triValue);
	}
	
	public static int countFactors2(int num){
		int numToFindFactors = num;
		int currentPrime = 2;
		HashMap<Integer, Integer> pfList = new HashMap<Integer, Integer>();
		if (num == 1){
			return 1;
		} else {
			while (numToFindFactors != 1){
				if (numToFindFactors%currentPrime == 0){
					numToFindFactors /= currentPrime;
					if (pfList.containsKey(currentPrime)){
						pfList.put(currentPrime, pfList.get(currentPrime) + 1);
					} else {
						pfList.put(currentPrime, 1);
					}
					currentPrime = 2;
				} else {
					currentPrime = nextPrime(currentPrime);
				}
			}
		}
		int output = 1;
		Iterator<Integer> pfListIterator = pfList.keySet().iterator();
		while (pfListIterator.hasNext()){
			Integer key = pfListIterator.next();
			output *= pfList.get(key)+1;
		}
		return output;
	}
	
	public static int nextPrime(int current){
		int test = current+1;
		double max = Math.sqrt(test);
		int currentTest = 2;
		while (true){
			if (test%currentTest == 0){
				currentTest = 2;
				test++;
				max = Math.sqrt(test);
			} else if (currentTest > max){
				return test;
			} else {
				currentTest++;
			}
		}
	}
}
