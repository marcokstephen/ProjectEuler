#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

struct dynarray {
   char** data; //array of strings
   int length;
   int maxPosition;
};

void insertArray(struct dynarray* array, int position, char* substring){
   if (position >= array->length){
      array->length *= 2;
      array->data = realloc(array->data,sizeof(char*) * array->length);
   }
   if (position > array->maxPosition){
      array->maxPosition = position;
      //keeps track of # of array inputs
   }
   *(array->data + position) = substring;
}

void destroy_array(struct dynarray* array){
   free(array->data);
   free(array);
}

int my_strcmp(const void* a, const void* b){
   char const* aa = *(char const**)a;
   char const* bb = *(char const**)b;
   return strcmp(aa,bb);
}

int score_name(char** data, int position){
   char* word = data[position];
   int length = strlen(word);
   int score = 0;
   for (int i = 0; i < length; i++){
      score = score + *(word+i) - 64; //A - 64 = 1
   }
   score *= (position+1);
   return score;
}

int main(void){
   FILE* pFile;
   int size = sizeof(char);
   int count = 46447; //size of names.txt file
   
   pFile = fopen("names.txt","r");
   if (pFile == NULL){
      printf("Cannot read file.\n");
      exit(EXIT_FAILURE);
   }
   
   char* buffer = malloc(sizeof(char) * 46448);
   fread(buffer,size,count,pFile);
   char* singleName = strtok(buffer,"\",");
   
   struct dynarray* array = malloc(sizeof(struct dynarray));
   char** data = malloc(sizeof(char*) * 8); //initial array len of 8
   array->data = data;
   array->length = 8;
   array->maxPosition = 0;
   
   int position = 0;
   while (singleName != NULL){
      insertArray(array, position, singleName);
      singleName = strtok(NULL,"\",");
      position++;
   }
   qsort(array->data,array->maxPosition+1,sizeof(char*),my_strcmp);
   
   int sum = 0;
   for (int i = 0; i <= array->maxPosition; i++){
      sum += score_name(array->data, i);
   }
   printf("%d\n",sum);
   free(buffer);
   fclose(pFile);
   destroy_array(array);
}
