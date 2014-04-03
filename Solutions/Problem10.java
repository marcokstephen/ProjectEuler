package projectten;

public class main {
	public static void main(String[] args){
		long sum = 2+3;
		int currentN = 3;
		int nextPrime = findNextPrime(3);
		while (nextPrime < 2000000){
			sum += nextPrime;
			nextPrime = findNextPrime(nextPrime);
		}
		System.out.println("The sum is " + sum);
	}
	
	public static int findNextPrime(int n){
		int numberToTest = n+2;
		while(true){
			if (isPrime(numberToTest)){
				return numberToTest;
			}
			numberToTest += 2;
		}
	}
	
	public static boolean isPrime(int n){
		int testValue = 3; //we start testing at 3 becuase
						//no even numbers should ever be passed in
		double maxValue = Math.sqrt(n);
		
		while (testValue <= maxValue){
			if (n%testValue == 0) return false;
			testValue += 2;
		}
		return true;
	}
}
