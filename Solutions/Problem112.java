public class Problem112 {
	static int numBouncy = 0;
	static int numNot = 98;
	public static void main(String[] args){
		int percentage = 99;
		int currentTest = 99;
		while ((numBouncy*100/(numBouncy+numNot) != percentage) || ((numBouncy*100)%(numBouncy+numNot) != 0)){
			if (bouncy(currentTest)){
				numBouncy++;
			} else {
				numNot++;
			}
			currentTest++;
		}
		System.out.println(currentTest-1);
	}
	
	public static boolean increasing(int number){
		String numString = "" + number;
		int min = 0;
		for (int i = 0; i < numString.length(); i++){
			int value = Character.getNumericValue(numString.charAt(i));
			if (value < min) return false;
			min = value;
		}
		return true;
	}
	public static boolean decreasing(int number){
		String numString = "" + number;
		int max = 10;
		for (int i = 0; i < numString.length(); i++){
			int value = Character.getNumericValue(numString.charAt(i));
			if (value > max) return false;
			max = value;
		}
		return true;
	}
	public static boolean bouncy(int number){
		if (!increasing(number) && !decreasing(number)) return true;
		return false;
	}
}