#include <stdio.h>
#include <stdbool.h>
#include <math.h>

bool isPrime(int n){
   double max = sqrt(n);
   for (int i = 2; i <= max; i++){
      if (n%i == 0) return false;
   }
   return true;
}

int nextPrime(int p){
   int test = p+1;
   while (!isPrime(test)){
      test++;
   }
   return test;
}

int main(void){
   int prime = 2;
   for (int i = 1; i < 10001; i++){
      prime = nextPrime(prime);
   }
   printf("%d\n",prime);
}
