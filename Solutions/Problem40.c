#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>

int main(void){
   char* array = malloc(sizeof(char) * 1000500);
   int i = 1;
   int len = 0;
   while(len < 1000000){
      char numString[10];
      sprintf(numString,"%d",i);
      strcat(array,numString);
      len += strlen(numString);
      i++;
   }
   int ans = (array[0] - 48) * (array[9] - 48) * (array[99] - 48) * (array[999] - 48) * (array[9999] - 48) * (array[99999] - 48) * (array[999999] - 48);
   printf("%d\n",ans);
   free(array);
}
