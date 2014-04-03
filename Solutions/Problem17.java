public class Problem17 {
	final static int ONETHOUSAND = 11;
	final static int ONE = 3;
	final static int TWO = 3;
	final static int THREE = 5;
	final static int FOUR = 4;
	final static int FIVE = 4;
	final static int SIX = 3;
	final static int SEVEN = 5;
	final static int EIGHT = 5;
	final static int NINE = 4;
	final static int HUNDRED = 7;
	final static int TWENTY = 6;
	final static int THIRTY = 6;
	final static int FORTY = 5;
	final static int FIFTY = 5;
	final static int SIXTY = 5;
	final static int SEVENTY = 7;
	final static int EIGHTY = 6;
	final static int NINETY = 6;
	final static int ELEVEN = 6;
	final static int TWELVE = 6;
	final static int THIRTEEN = 8;
	final static int FOURTEEN = 8;
	final static int FIFTEEN = 7;
	final static int SIXTEEN = 7;
	final static int SEVENTEEN = 9;
	final static int EIGHTEEN = 8;
	final static int NINETEEN = 8;
	final static int AND = 3;
	final static int TEN = 3;

	public static void main(String[] args){
		int sum = 0;
		for (int i = 1; i <= 1000; i++){
			int output = numberToString(i);
			sum += output;
		}
		System.out.println(sum);
	}
	
	static int numberToString(int i){
		int value = 0;
		if (i == 1000){
			return ONETHOUSAND;
		} else if (i >= 100){
			int UNCERTAIN = 0;
			int temp = i;
			temp /= 100;
			i = i - (temp*100);
			if (i == 0) UNCERTAIN = -3; //This is the value of AND
			if (temp == 1) {
				return ONE + HUNDRED + AND + UNCERTAIN + numberToString(i);
			} else if (temp == 2){
				return TWO + HUNDRED + AND + UNCERTAIN + numberToString (i);
			} else if (temp == 3){
				return THREE + HUNDRED + AND + UNCERTAIN + numberToString(i);
			} else if (temp == 4){
				return FOUR + HUNDRED + AND + UNCERTAIN + numberToString(i);
			} else if (temp == 5){
				return FIVE + HUNDRED + AND + UNCERTAIN + numberToString(i);
			} else if (temp == 6) {
				return SIX + HUNDRED + AND + UNCERTAIN + numberToString(i);
			} else if (temp == 7){
				return SEVEN + HUNDRED + AND + UNCERTAIN + numberToString(i);
			} else if (temp == 8) {
				return EIGHT + HUNDRED + AND + UNCERTAIN + numberToString(i);
			} else {
				return NINE + HUNDRED + AND + UNCERTAIN + numberToString(i);
			}
		} else if (i == 0){
			return 0;
		} else if (i == 1){
			return ONE;
		} else if (i == 2){
			return TWO;
		} else if (i == 3) {
			return THREE;
		} else if (i == 4) {
			return FOUR;
		} else if (i == 5) {
			return FIVE;
		} else if (i == 6) {
			return SIX;
		} else if (i == 7) {
			return SEVEN;
		} else if (i == 8) {
			return EIGHT;
		} else if (i == 9) {
			return NINE;
		} else if (i == 10) {
			return TEN;
		} else if (i == 11) {
			return ELEVEN;
		} else if (i == 12) {
			return TWELVE;
		} else if (i == 13) {
			return THIRTEEN;
		} else if (i == 14) {
			return FOURTEEN;
		} else if (i == 15) {
			return FIFTEEN;
		} else if (i == 16) {
			return SIXTEEN;
		} else if (i == 17) {
			return SEVENTEEN;
		} else if (i == 18) {
			return EIGHTEEN;
		} else if (i == 19) {
			return NINETEEN;
		} else { //20 <= i <=99
			int temp = i/10;
			i = i - (temp*10);
			if (temp == 2){
				return TWENTY + numberToString(i);
			} else if (temp == 3){
				return THIRTY + numberToString(i);
			} else if (temp == 4){
				return FORTY + numberToString(i);
			} else if (temp == 5){
				return FIFTY + numberToString(i);
			} else if (temp == 6){
				return SIXTY + numberToString(i);
			} else if (temp == 7){
				return SEVENTY + numberToString(i);
			} else if (temp == 8){
				return EIGHTY + numberToString(i);
			} else if (temp == 9){
				return NINETY + numberToString(i);
			}
		}
		return value;
	}
}
