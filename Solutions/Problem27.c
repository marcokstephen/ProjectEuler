#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <math.h>

bool isPrime(int n){
  int num = abs(n);
  int max = sqrt(num);
  int currentTest = 2;
  while (currentTest <= max){
    if (num%currentTest == 0) return false;
    currentTest++;
  }
  return true;
}

int main(void){
  int maxN = 0;
  int maxA = 0;
  int maxB = 0;
  for (int b = -999; b < 1000; b++){
    if (isPrime(b)){
      for (int a = -999; a < 1000; a++){
         int n = 0;
	      int product = n*n + a*n + b;
	      while(isPrime(product)){
	         n++;
	         product = n*n + a*n + b;
	      }
	      if (n > maxN){
	         maxN = n;
	         maxA = a;
	         maxB = b;
	      }
      }//and a for loop
    }//if b is composite, when n is 0 the equation cannot be prime
  }//end b for loop
  printf("%d\n",maxA*maxB);
}
