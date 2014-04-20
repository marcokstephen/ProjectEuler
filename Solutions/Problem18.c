#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

//num of rows, user must set
int numRows = 15;

int arraylen = 0; //gets changed in main
struct node{
   int key;
   int maxbelow;
};

int max(int a, int b){
   if (a > b) return a;
   return b;
}

struct node** add_data(void){
   //c&p from projecteuler into numbers.txt
   FILE* fp = fopen("numbers.txt","r");
   int i = 0;
   int count = 0;
   struct node** nodeArray = malloc(sizeof(struct node*) * arraylen);
   fscanf(fp,"%d",&i);
   
   while (!feof (fp)){
      struct node* new = malloc(sizeof(struct node));
      new->key = i;
      new->maxbelow = 0;
      nodeArray[count] = new;
      fscanf(fp,"%d",&i);
      count++;
   }   
   fclose(fp);
   return nodeArray;
}

void destroy_data(struct node** array){
   for (int i = 0; i < arraylen; i++){
      free(array[i]);
   }
   free(array);
}

int findCurrentRow(int i){
   int input = i + 1;
   int test = 0;
   int testlength = 0;
   while (testlength < input){
      testlength = test*(test+1)/2;
      test++;
   }
   return test-1;
}

void find_maxbelow(struct node** array){
   for (int i = arraylen - 1; i >= 0; i--){
      int currentRow = findCurrentRow(i);
      if (currentRow == numRows){
         array[i]->maxbelow = array[i]->key;
      } else {
         int leftbelow = array[i+currentRow]->maxbelow;
         int rightbelow = array[i+currentRow+1]->maxbelow;
         array[i]->maxbelow = array[i]->key + max(leftbelow,rightbelow);
      }
   }
}

int main(void){
   arraylen = numRows*(numRows+1)/2;
   struct node** array = add_data();
   find_maxbelow(array);
   printf("%d\n",array[0]->maxbelow);
   destroy_data(array);
}
