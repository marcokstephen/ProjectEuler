#include <stdio.h>
#include <stdbool.h>
#include <math.h>

bool isPrime(int num){
  int max = sqrt(num);
  int currentTest = 2;
  while (currentTest <= max){
    if (num%currentTest == 0) return false;
    currentTest++;
  }
  return true;
}

int nextPrime(int num){
   if (num == 2) return 3;
   int currentTest = num+2;
   while(1){
      if (isPrime(currentTest)) return currentTest;
      currentTest += 2;
   }
}

bool conjecture(int num){
   int currentTestPrime = 2;
   int maxPrime = num - 2;
   while (currentTestPrime <= maxPrime){
      int sqrTest = 1;
      while (currentTestPrime + 2*sqrTest*sqrTest <= num){
         if (currentTestPrime + 2*sqrTest*sqrTest == num) return true;
         sqrTest++;
      }
      currentTestPrime = nextPrime(currentTestPrime);
   }
   return false;
}

int nextTest(int num){
   int currentTest = num + 2;
   while(true){
      if (!isPrime(currentTest)) return currentTest;
      currentTest += 2;
   }
}

int main(void){
   int test = 9;
   while (conjecture(test)){
      test = nextTest(test);
   }
   printf("%d\n",test);
}
