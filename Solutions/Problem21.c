#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include <math.h>

struct llnode {
   int value;
   struct llnode* next;
};

struct list {
   struct llnode* front;
};

struct list* get_divisors(int n){
   struct list* new = malloc(sizeof(struct list));
   struct llnode* newNode = malloc(sizeof(struct llnode));
   new->front = newNode;
   newNode->value = 1;
   
   int temp = 2;
   double max = sqrt(n);
   while (temp < max){
      if (n%temp == 0){
         struct llnode* divOne = malloc(sizeof(struct llnode));
         struct llnode* divTwo = malloc(sizeof(struct llnode));
         divOne->value = temp;
         divOne->next = divTwo;
         divTwo->value = n/temp;
         newNode->next = divOne;
         newNode = divTwo;
      }
      temp++;
   }
   
   return new;
}

void destroyListNodes(struct llnode* node){
   while (node->next != NULL){
      destroyListNodes(node->next);
   }
   free(node);
}

void destroyList(struct list* list){
   destroyListNodes(list->front);
   free(list);
}

int sumDivisors(int n){
   int sum = 0;
   struct list* divisors = get_divisors(n);
   struct llnode* front = divisors->front;
   while (front != NULL){
      sum += front->value;
      front = front->next;
   }
   return sum;
}

bool isAmicable(int n){
   int sum = sumDivisors(n);
   if (sum == n) return false;
   int sum2 = sumDivisors(sum);
   if (sum2 == n) return true;
   return false;
}

int main(void){
   int sum;
   for (int i = 1; i < 10000; i++){
      if (isAmicable(i)){
         sum += i;
      }
   }
   printf("%d\n",sum);
}
