#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
int width = 80;
int length = 0; //automatically changed in main

struct node{
	int key;
	int minbelow;
};

//replace all "," with " " in matrix.txt
struct node** add_data(void){
	FILE* fp = fopen("matrix.txt","r");
	if (fp == NULL){
		printf("Cannot read file\n");
		exit(EXIT_FAILURE);
	}
	struct node** nodeArray = malloc(sizeof(struct node*)*length);
	int i = 0;
	int count = 0;
	fscanf(fp,"%d",&i);
	
	while (!feof (fp)){
		struct node* new = malloc(sizeof(struct node));
		new->key = i;
		new->minbelow = 0;
		nodeArray[count] = new;
		fscanf(fp,"%d",&i);
		count++;
	}
	fclose(fp);
	return nodeArray;
}

int min(int a, int b){
	if (a < b) return a;
	return b;
}

void find_minbelow(struct node** array){
	int posn = length - 1;
	array[posn]->minbelow = array[posn]->key;
	posn--;
	while (posn >= 0){
		if ((posn+1)%width == 0){ //number is on right column of matrix
			array[posn]->minbelow = array[posn]->key + array[posn+width]->minbelow;
		} else if (posn >= (length - width)) {
			array[posn]->minbelow = array[posn]->key + array[posn+1]->minbelow;
		} else {
			array[posn]->minbelow = array[posn]->key + min(array[posn+1]->minbelow, array[posn+width]->minbelow);
		}
		posn--;
	}
}

void destroy_data(struct node** array){
	for (int i = 0; i < length; i++){
		free(array[i]);
	}
	free(array);
}

int main(void){
    length = width*width;
	struct node** array = add_data();
	find_minbelow(array);
    printf("%d\n",array[0]->minbelow);
	destroy_data(array);
}
