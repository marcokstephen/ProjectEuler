#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

//fread/strtok

int main(void){
   FILE* pFile;
   int size = sizeof(char);
   int count = 46447;
   
   pFile = fopen("names.txt","r");
   if (pFile == NULL){
      printf("Cannot read file.");
      exit(EXIT_FAILURE);
   }
   
   char* buffer = malloc(sizeof(char) * 46448);
   fread(buffer,size,count,pFile);
   printf("%s\n",buffer);
   free(buffer);
}
