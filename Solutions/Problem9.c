#include <stdio.h>

int main(void){
   for (int i = 0; i < 998; i++){
      for (int j = i+1; j < 999; j++){
         for (int k = j+1; k < 1000; k++){
            if (i+j+k == 1000){
               int sum = i*i+j*j;
               int answer = k*k;
               
               if (sum == answer){
                  printf("%d",i*j*k);
               }
            }
         }
      }
   }
}
