class Problem2 {
    public static void main(String[] args){
	int first = 1;
	int second = 2;
	int third = 3;
	int sum = 2;
	while (third <= 4000000){
	    first = second;
	    second = third;
	    third = first + second;
	    if (third%2 == 0) sum += third;
	}
	System.out.println(sum);
    }
}