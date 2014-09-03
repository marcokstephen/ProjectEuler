import java.util.ArrayList;
import java.util.List;

public class Problem26 {
	public static void main(String[] args){
		int maxLength = 0;
		int maxDenom = 0;
		for (int n = 2; n <= 999; n++){
			int remainder = 1;
			boolean solved = false;
			List<Integer> remainderList = new ArrayList<Integer>();
			while (!solved){
				while (remainder < n){
					remainder *= 10;
				}
				remainder %= n;

				if (remainder == 0){
					solved = true;
				} else if (remainderList.contains(remainder)){
					int numRecurring = remainderList.size();
					if (numRecurring > maxLength){
						maxLength = numRecurring;
						maxDenom = n;
					}
					solved = true;
				} else {
					remainderList.add(remainder);
				}
			}
		}
		System.out.println(maxDenom);
	}
}
