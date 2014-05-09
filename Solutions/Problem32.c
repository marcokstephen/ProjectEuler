#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

int product = 0;

void insert_array(int* array, int n){
   array[n]++;
   if (array[n] == 1){
      product += n;
   }
}

bool member(char* numString, int num){
   char* numberString = numString;
   char test = num+48;
   while(*numberString){
      if (*numberString == test) return true;
      numberString++;
   }
   return false;
}

bool checkValid(char* numString){
   if (!member(numString,0) && member(numString,1) && member(numString,2)
    && member(numString,3) && member(numString,4) && member(numString,5)
     && member(numString,6) && member(numString,7) && member(numString,8)
      && member(numString,9)){
         return true;
      }
   return false;
}

int main(void){
   int* array = malloc(sizeof(int) * 10000); //max supported int is 9999
   int tempProd = 0;
   for (int a = 2; a < 9876; a++){
      for (int b = 2; b < 9876; b++){
         tempProd = a*b;
         if (tempProd > 9999) break;
         char numString[10];
         sprintf(numString,"%d%d%d",a,b,tempProd);
         if (checkValid(numString)){
            insert_array(array, tempProd);         
         }
      }
   }
   free(array);
   printf("%d\n",product);
}
