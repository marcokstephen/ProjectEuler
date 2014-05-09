
public class Problem37 {
	static int sum = 0;
	static int count = 0;
	
	static boolean isPrime(int n){
		if (n == 1) return false;
		double max = Math.sqrt(n);
		int currentTest = 2;
		while (currentTest <= max){
			if (n%currentTest == 0) return false;
			currentTest++;
		}
		return true;
	}
	
	static boolean rightToLeft(int n){
		int num = n;
		while (num != 0){
			if (!isPrime(num)) return false;
			num /= 10;
		}
		return true;
	}
	
	static boolean leftToRight(int n){
		String num = ""+n;
		String parsedNum = ""+n;
		int newNum = Integer.parseInt(parsedNum);
		while (!parsedNum.equals("")){
			newNum = Integer.parseInt(parsedNum);
			if (!isPrime(newNum)) return false;
			num = ""+ newNum;
			parsedNum = num.substring(1);
		}
		return true;
	}
	
	public static void main(String[] args){
		int num = 10;
		while (count < 11){
			if (rightToLeft(num) && leftToRight(num)){
				count++;
				sum += num;
				System.out.println("Count at " + count + ": " + num);
			}
			num++;
		}
		System.out.println(sum);
	}
}
