/* This code "guesses" the answer, because I haven't yet proven the upper limit. The guess is correct,
but not yet proven. */

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
  int* data = malloc(sizeof(int) * 6);
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
  } else {
    while (value >= 10){
      array->data[currentPosition] = pow((value%10),5);
      value /= 10;
      currentPosition++;
    }
    array->data[currentPosition] = pow(value,5);
  }
  array->len = currentPosition;
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
  for (int i = 0; i < 999999; i++)){
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
