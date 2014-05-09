#include <stdio.h>
#include <stdbool.h>
#include <string.h>

bool member(char* numString, int num){
   char* numberString = numString;
   char test = num+48;
   while(*numberString){
      if (*numberString == test) return true;
      numberString++;
   }
   return false;
}

bool isPan(char* numString){
   if (!member(numString,0) && member(numString,1) && member(numString,2)
    && member(numString,3) && member(numString,4) && member(numString,5)
     && member(numString,6) && member(numString,7) && member(numString,8)
      && member(numString,9)){
         return true;
      }
   return false;
}

int main(void){
   for (int i = 1; i < 9999; i++){
      int multiple = 1;
      char numString[20] = "";
      while (strlen(numString) < 9){
         char tempNumString[10];
         int product = i*multiple;
         sprintf(tempNumString,"%d",product);
         strcat(numString,tempNumString);
         multiple++;
      }
      if (strlen(numString) == 9 && isPan(numString)){
         printf("%s\n",numString);
      }
   }
   printf("Pick the biggest of this list (there aren't many).\n\n");
}
