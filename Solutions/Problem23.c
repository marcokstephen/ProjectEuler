//there's a bug so it's not completely solved yet...
//answer within 0.25% of real answer

#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <math.h>

struct dynarray{
   int* data;
   int len;
   int maxlen;
};

struct dynarray* create_array(void){
   struct dynarray* array = malloc(sizeof(struct dynarray));
   array->len = 0;
   array->maxlen = 8;
   int* data = malloc(sizeof(int) * array->maxlen);
   array->data = data;
   array->data[0] = 0; //for this program we start at index 1
   return array;
}

void destroy_array(struct dynarray* array){
   free(array->data);
   free(array);
}

void add_to_array(struct dynarray* array, int i){
   if (array->len >= array->maxlen-1){
      array->maxlen *= 2;
      array->data = realloc(array->data, sizeof(int) * array->maxlen);
   }
   array->len++;
   array->data[array->len] = i;
}

void print_array(struct dynarray* array){
   int* data = array->data;
   for (int i = 1; i <= array->len; i++){
      printf("%d ",data[i]);
   }
   printf("\n");
}

int sum_array(struct dynarray* array){
   int sum = 0;
   for (int i = 1; i <= array->len; i++){
      sum += array->data[i];
   }
   return sum;
}

bool member(int n, struct dynarray* array){
   int* data = array->data;
   for (int i = 1; i <= array->len; i++){
      if (data[i] == n) return true;
   }
   return false;
}

// ======= ENDS ARRAY IMPLEMENTATION ======= 

//sums the divisors of n
int sumDivisors(int n){
   int sum = 1;
   double max = sqrt(n);
   int test = 2;
   while (test < max){
      if (n%test == 0){
         sum += test;
         sum += (n/test);
      }
      test++;
   }
   return sum;
}

bool isComposed(struct dynarray* aList, int i){
   int len = aList->len;
   int* data = aList->data;
   int position = 1;
   while (position <= len){
      int test = i - data[position];
      if (test <= 0){
         return false;
      } else if (member(test,aList)){
         printf("Not adding %d: %d,%d\n",i,test,data[position]);
         return true;
      } else {
         position++;
      }
   }
   return false;
}

int main(void){
   FILE* out = fopen("out.txt","w");
   struct dynarray* abundantList = create_array();
   for (int i = 1; i <= 28123 ; i++){
      if(sumDivisors(i) > i){
         add_to_array(abundantList,i);
      }
   }
   struct dynarray* answerList = create_array();
   for (int i = 1; i <= 28123; i++){
      if (!isComposed(abundantList, i)){
        add_to_array(answerList,i);
        fprintf(out,"ADDING %d\n",i);
      } else {
         fprintf(out,"Not adding %d\n",i);
      }
   }
   print_array(answerList);
   printf("%d\n",sum_array(answerList));
   destroy_array(answerList);
   destroy_array(abundantList);
}
