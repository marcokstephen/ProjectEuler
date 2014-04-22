class Problem4{
  public static void main(String[] args){
    int max = 0;
    for (int i = 1; i <= 999; i++){
      for (int m = 1; m <= 999; m++){
        if (isPalindrome(m*i)){
          if (m*i > max) max = m*i;
        }
      }
    }
    System.out.println(max);
  }
  
  public static boolean isPalindrome(int i){
    String number = i+"";
    String reverse = new StringBuilder(number).reverse().toString();
    if (reverse.equals(number)){
      return true;
    }
    return false;
  }
}
