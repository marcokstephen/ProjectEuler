//less than 30 seconds
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Problem179 {

	public final static List<Integer> primeList = generatePrimeList(); //size is 446
	public final static int pfListSize = primeList.size();
	
	public static void main(String[] args){
		int numFactorsN = countFactors(2);
		int numFactorsNP1 = countFactors(3);
		int count = 0;
		for (int m = 2; m < 10000000; m++){
			numFactorsN = numFactorsNP1;
			numFactorsNP1 = countFactors(m+1);
			if (numFactorsN == numFactorsNP1){
				count++;
			}
		}
		System.out.println(count);
	}

	public static int countFactors(int numToFind){
		HashMap<Integer, Integer> pfList = new HashMap<Integer, Integer>();
		int output = 1;
		int num = numToFind;
		if (num == 1) return 1;
		double max = Math.sqrt(num);
		for (int i = 0; i < pfListSize; i++){
			int currentPrime = primeList.get(i);
			if (currentPrime > max){
				break;
			} else {
				if (num%currentPrime == 0){
					addToList(currentPrime, pfList);
					num /= currentPrime;
					i--;
				}
			}
		}
		Iterator<Integer> pfListIterator = pfList.keySet().iterator();
		while (pfListIterator.hasNext()){
			Integer key = pfListIterator.next();
			output *= pfList.get(key)+1;
		}
		
		if (num != 1){
			return output * 2;
		}
		return output;
	}
	
	public static void addToList(int number, HashMap<Integer, Integer> pfList){
		if (pfList.containsKey(number)){
			pfList.put(number, pfList.get(number)+1);
		} else {
			pfList.put(number,1);
		}
	}
	
	public static boolean isPrime(int num){
		double max = Math.sqrt(num);
		int currentTest = 2;
		while (currentTest <= max){
			if (num%currentTest == 0) return false;
			currentTest++;
		}
		return true;
	}
	public static int nextPrime(int current){
		int test = current+1;
		while(true){
			if (isPrime(test)) return test;
			test++;
		}
	}
	public static List<Integer> generatePrimeList(){
		List<Integer> primeList = new ArrayList<Integer>();
		//highest prime we need to list is sqrt(10000000) = 3163
		int currentPrime = 2;
		while (currentPrime < 3163){
			primeList.add(currentPrime);
			currentPrime = nextPrime(currentPrime);
		}
		return primeList;
	}
}