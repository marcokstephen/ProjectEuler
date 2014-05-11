#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <math.h>

//a bad solution - takes about 3 minutes

struct llnode{
   int data;
   struct llnode* next;
};

struct llist{
   struct llnode* front;
   struct llnode* back;
   int len;
};

struct llist* create_list(void){
   struct llist* new = malloc(sizeof(struct llist));
   new->front = NULL;
   new->back = NULL;
   new->len = 0;
   return new;
}

void add_to_back(struct llist* list, int data){
   struct llnode* new = malloc(sizeof(struct llnode));
   new->data = data;
   new->next = NULL;
   if (list->front == NULL){
      list->front = new;
   } else {
      list->back->next = new;
   }
   list->back = new;
   list->len++;
}

bool isPrime(int num){
  int max = sqrt(num);
  int currentTest = 2;
  while (currentTest <= max){
    if (num%currentTest == 0) return false;
    currentTest++;
  }
  return true;
}

int nextPrime(int num){
   if (num == 2) return 3;
   int currentTest = num+2;
   while(1){
      if (isPrime(currentTest)) return currentTest;
      currentTest += 2;
   }
}

bool member(struct llist* list, int data){
   struct llnode* front = list->front;
   while (front != NULL){
      if (front->data == data) return true;
      front = front->next;
   }
   return false;
}

int numPrimeFactors(int n){
   int currentTest = 2;
   int unique = 0;
   int num = n;
   struct llist* factors = create_list();
   while (num != 1){
      if (unique > 4){
         return 5;
      } else if (num%currentTest == 0){
         if (!member(factors, currentTest)){
            unique++;
            add_to_back(factors, currentTest);
         } else {
         }
         num /= currentTest;
         currentTest = 2;
      } else {
         currentTest = nextPrime(currentTest);
      }
   }
   return unique;
}

int main(void){
   int currentTest = 1;
   while(true){
      if (numPrimeFactors(currentTest) == 4 &&
         numPrimeFactors(currentTest+1) == 4 &&
         numPrimeFactors(currentTest+2) == 4 &&
         numPrimeFactors(currentTest+3) == 4){
         printf("%d\n",currentTest);
         exit(EXIT_SUCCESS);
      } else {
         currentTest++;
      }
   }
}
