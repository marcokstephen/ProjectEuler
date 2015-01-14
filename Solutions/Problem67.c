#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

//num of rows, user must set (because this code is used for the Problem 18 version as well)
int numRows = 100;

int arraylen = 0; //gets changed in main
struct node{
   int key;
   int maxbelow;
};

//find max of two numbers
int max(int a, int b){
   if (a > b) return a;
   return b;
}

//reads in the data from the triangle.txt file provided by ProjectEuler
//creates nodes for each number in the triangle
struct node** add_data(void){
   FILE* fp = fopen("triangle.txt","r");
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

//destroying the nodes and freeing the memory
void destroy_data(struct node** array){
   for (int i = 0; i < arraylen; i++){
      free(array[i]);
   }
   free(array);
}

//find the current row of the triangle depending on the array's index
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

//finding the value of the maximum path
void find_maxbelow(struct node** array){
   for (int i = arraylen - 1; i >= 0; i--){ //go backwards and build from the bottom of the triangle to the top
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
