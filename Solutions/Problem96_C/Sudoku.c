#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include <string.h>

#define BOARDLEN 81

struct IntNode{
    int number;
    struct IntNode* next;
    struct IntNode* previous;
};

struct IntList{
    struct IntNode* first;
    struct IntNode* last;
    int length;
};

typedef struct IntList* PossibleList;

PossibleList createPossibleList(void){
    PossibleList pl = malloc(sizeof(struct IntList));
    pl->length = 0;
    pl->first = NULL;
    pl->last = NULL;
    return pl;
}

void addToPossibleList(PossibleList pl, int number){
    struct IntNode* newNode = malloc(sizeof(struct IntNode));
    newNode->number = number;
    newNode->next = NULL;
    if (pl->length == 0){
        pl->first = newNode;
        newNode->previous = NULL;
    } else {
        newNode->previous = pl->last;
        pl->last->next = newNode;
    }
    pl->last = newNode;
    pl->length++;
}

void destroyPossibleList(PossibleList pl){
    struct IntNode* currentNode = pl->first;
    struct IntNode* nextNode = NULL;
    while (currentNode != NULL){
        nextNode = currentNode->next;
        free(currentNode);
        currentNode = NULL;
        currentNode = nextNode;
    }
    free(pl);
    pl = NULL;
}

void removeValueFromPossibleList(PossibleList pl, int valueToRemove){
    struct IntNode* currentNode = pl->first;
    while (currentNode != NULL){
        if (currentNode->number == valueToRemove){
            if (currentNode->previous == NULL){
                pl->first = currentNode->next;
            } else if (currentNode->next == NULL){
                pl->last = currentNode->previous;
            } else {
                currentNode->previous->next = currentNode->next;
            }
            pl->length--;
            free(currentNode);
            currentNode = NULL;
        } else {
            currentNode = currentNode->next;
        }
    }
}

struct IndividualCell{
    int value;
    bool assigned;
    PossibleList possibles;
};

typedef struct IndividualCell* Cell;

struct GameBoard{
    Cell* cells;
    struct GameBoard* parentBoard;
};

typedef struct GameBoard* Board;

Board createBoard(char* boardString){
    Board b = malloc(sizeof(struct GameBoard));
    b->parentBoard = NULL;
    b->cells = malloc(sizeof(Cell)*BOARDLEN);
    for (int i = 0; i < BOARDLEN; i++){
        char c = *(boardString+i);
        int integerValue = ((int)c)-48;
        Cell newCell = malloc(sizeof(struct IndividualCell));
        newCell->value = integerValue;
        if (integerValue == 0){
            newCell->assigned = false;
        } else {
            newCell->assigned = true;
        }
        newCell->possibles = NULL;
        
        *((b->cells)+i) = newCell;
    }
    return b;
}

Cell* getSquareByCellIndex(Board b, int index){
    Cell* squareArray = malloc(sizeof(Cell)*9);
    
    return squareArray; 
}

void establishPossibleLists(Board b){
    for (int i = 0; i < BOARDLEN; i++){
        if (!b->cells[i]->assigned){
            b->cells[i]->possibles = createPossibleList();
            for (int j = 1; j < 10; j++){
                addToPossibleList(b->cells[i]->possibles, j);
            }
        }
    }
}

/*void removeConfirmedFromPossibleLists(Board b){
    Cell* = b->cells;
    bool changeOccurred = false;

    do {


    } while (changeOccurred);
}*/
    

int main(void){
    char puzzle[82] = "003020600900305001001806400008102900700000008006708200002609500800203009005010300";
    Board b = createBoard(puzzle);
    establishPossibleLists(b);
    for (int i = 0; i < 81; i++){
        printf("The %d is %d assigned, with a value of %d\n",(i+1),b->cells[i]->assigned,b->cells[i]->value);
        if (!b->cells[i]->assigned){
            printf("The possible values are ");
            PossibleList pl = b->cells[i]->possibles;
            struct IntNode* currentNode = NULL;
            if (pl != NULL) currentNode = pl->first;
            while (currentNode != NULL){
                printf("%d ",currentNode->number);
                currentNode = currentNode->next;
            }
            printf("\n");
        }
    }
}
