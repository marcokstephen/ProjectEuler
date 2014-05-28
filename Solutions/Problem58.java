public class Problem58 {
	static int composites = 5;
	static int primes = 8;
	static int width = 7;
	public static void main(String[] args){
	while ((primes*100)/(composites+primes) >= 10){
			width += 2;
			generate_values(width);
		}
		System.out.println("Answer: " + width);
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
	
	public static void generate_values(int width){
		int lr = width*width;
		int ll = lr-width+1;
		int ul = ll-width+1;
		int ur = ul-width+1;
		
		if (isPrime(lr)){
			primes++;
		} else {
			composites++;
		}
		if (isPrime(ll)){
			primes++;
		} else {
			composites++;
		}
		if (isPrime(ul)){
			primes++;
		} else {
			composites++;
		}
		if (isPrime(ur)){
			primes++;
		} else {
			composites++;
		}
	}
}
