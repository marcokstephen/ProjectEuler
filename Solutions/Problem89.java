import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Problem89 {
	final static int M = 1000;
	final static int D = 500;
	final static int C = 100;
	final static int L = 50;
	final static int X = 10;
	final static int V = 5;
	final static int I = 1;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader("roman.txt"));
		String line;
		int counter = 0;
		while ((line = br.readLine()) != null){
			int linelength = line.length();
			int temp = romanToInt(line);
			String minRoman = intToRoman(temp);
			int newlength = minRoman.length();
			int difference = linelength - newlength;
			counter += difference;
		}
		br.close();
		System.out.println("Difference: " + counter);
	}
	
	public static int romanToInt(String roman){
		int output = 0;
		int stringlen = roman.length();
		while (stringlen != 0){
			if (roman.charAt(0) == 'M'){
				output += 1000;
				roman = roman.substring(1);
				stringlen--;
			} else if (roman.charAt(0) == 'C'){
				if (stringlen != 1 && roman.charAt(1) == 'D'){
					output += 400;
					roman = roman.substring(2);
					stringlen -= 2;
				} else if (stringlen != 1 && roman.charAt(1) == 'M'){
					output += 900;
					roman = roman.substring(2);
					stringlen -= 2;
				} else {
					output += 100;
					roman = roman.substring(1);
					stringlen--;
				}
			} else if (roman.charAt(0) == 'D'){
				output += 500;
				roman = roman.substring(1);
				stringlen--;
			} else if (roman.charAt(0) == 'L'){
				output += 50;
				roman = roman.substring(1);
				stringlen--;
			} else if (roman.charAt(0) == 'X'){
				if (stringlen != 1 && roman.charAt(1) == 'L'){
					output += 40;
					roman = roman.substring(2);
					stringlen -= 2;
				} else if (stringlen != 1 && roman.charAt(1) == 'C'){
					output += 90;
					roman = roman.substring(2);
					stringlen -= 2;
				} else {
					output += 10;
					roman = roman.substring(1);
					stringlen--;
				}
			} else if (roman.charAt(0) == 'V'){
				output += 5;
				roman = roman.substring(1);
				stringlen--;
			} else {
				if (stringlen != 1 && roman.charAt(1) == 'V'){
					output += 4;
					roman = roman.substring(2);
					stringlen -= 2;
				} else if (stringlen != 1 && roman.charAt(1) == 'X'){
					output += 9;
					roman = roman.substring(2);
					stringlen -= 2;
				} else {
					output += 1;
					roman = roman.substring(1);
					stringlen--;
				}
			}
		} //end while loop
		return output;
	}
	
	public static String intToRoman(int number){
		String output = "";
		int current = number;
		while (current != 0){
			if (current >= 1000){
				output += "M";
				current -= M;
			} else if (current >= 900){
				output = output + intToRoman(100) + "M";
				current = current + 100 - M;
			} else if (current >= 500){
				output += "D";
				current -= D;
			} else if (current >= 400){
				output = output + intToRoman(100) + "D";
				current = current + 100 - D;
			} else if (current >= 100){
				output += "C";
				current -= C;
			} else if (current >= 90){
				output = output + intToRoman(10) + "C";
				current = current + 10 - C;
			} else if (current >= 50){
				output += "L";
				current -= L;
			} else if (current >= 40){
				output = output + intToRoman(10) + "L";
				current = current + 10 - L;
			} else if (current >= 10){
				output += "X";
				current -= X;
			} else if (current >= 9){
				output = output + intToRoman(1) + "X";
				current = current + 1 - X;
			} else if (current >= 5){
				output += "V";
				current -= V;
			} else if (current >= 4){
				output = output + intToRoman(1) + "V";
				current = current + 1 - V;
			} else {
				output += "I";
				current -= I;
			}
		}
		return output;
	}
}
