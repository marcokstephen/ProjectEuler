/* This code finds the answer very quickly, but I'm not yet sure how to prove that this is the
correct answer. The code uses brute force to test all numbers, and needs to be manually stopped.
Basically I am "guessing" that the most recent number it finds is in fact the correct answer. */

#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#include <stdbool.h>

struct dynarray{
  int* data;
  int len;
};

struct dynarray* create_array(void){
  struct dynarray* dynarray = malloc(sizeof(struct dynarray));
  int* data = malloc(sizeof(int) * 12); //assumes no number greater than 12 digits (999 billion)
  dynarray->data = data;
  dynarray->len = 0;
}

struct dynarray* numToDigits(int num){
  struct dynarray* array = create_array();
  int currentPosition = 0;
  int value = num;
  if (value < 10){
    array->data[currentPosition] = pow(value,5);
    currentPosition++;
  } else { //value > 19
    while (value >= 10){
      array->data[currentPosition] = pow((value%10),5);
      value /= 10;
      currentPosition++;
    }
    array->data[currentPosition] = pow(value,5);
  }
  array->len = currentPosition;
}

void print_array(struct dynarray* array){
  int length = array->len;
  for (int i = 0; i <= length; i++){
    printf("%d ",array->data[i]);
  }
  printf("\n");
}

void destroy_array(struct dynarray* array){
  free(array->data);
  free(array);
}

int sum_array(struct dynarray* array){
  int sum = 0;
  for (int i = 0; i <= array->len; i++){
    sum += array->data[i];
  }
  return sum;
}

int main(void){
  int currentTest = 2;
  int finalSum = 0;
  while(1){
    struct dynarray* number = numToDigits(currentTest);
    if (sum_array(number) == currentTest){
      printf("%d",currentTest);
      finalSum += currentTest;
      printf("Current sum: %d\n",finalSum);
    }
    destroy_array(number);
    currentTest++;
  }
}