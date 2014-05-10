
public class Problem39 {
	static void findMax(int[] array){
		int length = array.length;
		int max = 0;
		int maxValue = 0;
		for (int i = 0; i < length; i++){
			if (array[i] > maxValue){
				max = i;
				maxValue = array[i];
			}
		}
		System.out.println(max);
	}
	public static void main(String[] args){
		int[] array = new int[1001];
		for (int a = 1; a < 500; a++){
			for (int b = 1; b < 500; b++){
				double c = Math.sqrt(a*a+b*b);
				int intC = (int)c;
				if (intC == c && (a+b+c)<=1000){
					array[a+b+intC]++;
				}
			}
		}
		findMax(array);
	}
}
