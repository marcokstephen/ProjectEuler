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

void add_to_array(struct dynarray* array, int i){
   if (array->len >= array->maxlen-1){
      array->maxlen *= 2;
      array->data = realloc(array->data, sizeof(int) * array->maxlen);
   }
   array->len++;
   array->data[array->len] = i;
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
   while (test <= max){
      if (n%test == 0){
         sum += test;
         if (test != max){
            sum += (n/test);
         }
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
         return true;
      } else {
         position++;
      }
   }
   return false;
}

int main(void){
   int sum = 0;
   struct dynarray* abundantList = create_array();
   for (int i = 1; i <= 28123 ; i++){
      if(sumDivisors(i) > i){
         add_to_array(abundantList,i);
      }
   }
   for (int i = 1; i <= 28123; i++){
      if (!isComposed(abundantList, i)){
        sum += i;
      }
   }
   printf("%d\n",sum);
   free(abundantList->data);
   free(abundantList);
}
