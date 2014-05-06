public class Problem28 {
	public static void main(String[] args){
		int maxWidth = 1001;
		int currentWidth = 1;
		int sum = 1;
		int previousNum = 1;
		int increment = 2;
		
		while (currentWidth < maxWidth){
			for (int i = 0; i < 4; i++){
				sum = sum + increment + previousNum;
				previousNum = previousNum + increment;
			}
			currentWidth += 2;
			increment += 2;
		}
		System.out.println(sum);
	}
}
