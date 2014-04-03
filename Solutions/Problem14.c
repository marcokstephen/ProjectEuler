#include <stdio.h>
int maxlength = 0;
//OUTLINE -- DOESN'T WORK

int findCollatz(int i){
   int length = 1;
   int number = i;
   while (number != 1){
      if (number%2 == 0){
         number /= 2;
      } else {
         number = 3*number + 1;
      }
      length++;
   }
   return length;
}

int main(void){
   for (int i = 1; i < 1000000; i++){
      int length = findCollatz(i);
      if (length > maxlength) maxlength = length;
   }
   printf("%d\n",maxlength);
}
