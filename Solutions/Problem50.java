public class Problem50 {
	public static boolean isPrime(int number){
		int currentTest = 2;
		double max = Math.sqrt(number);
		while (currentTest <= max){
			if (number%currentTest == 0) return false;
			currentTest++;
		}
		return true;
	}
	
	public static int nextPrime(int current){
		if (current == 2) return 3;
		int currentTest = current+2;
		while(true){
			if (isPrime(currentTest)) return currentTest;
			currentTest++;
		}
	}
	public static void main(String[] args){
		int max = 1000000;
		int maxLength = 0;
		int maxSum = 0;
		
		int startingPrime = 2;
		int currentPrime = 2;
		int sum = currentPrime;
		int roundSum = sum;
		int roundLength = 0;
		int length = 0;
		
		while (startingPrime < max){
			while(sum < max){
				currentPrime = nextPrime(currentPrime);
				sum += currentPrime;
				length++;
				if (isPrime(sum)){
					roundSum = sum;
					roundLength = length;
				}
			}
			if (roundLength > maxLength && roundSum < max){
				maxSum = roundSum;
				maxLength = roundLength;
			}
			startingPrime = nextPrime(startingPrime);
			currentPrime = startingPrime;
			sum = currentPrime;
			roundLength = 0;
			length = 0;
		}
		System.out.println(maxSum);
	}
}
