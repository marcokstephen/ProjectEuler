#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

struct day {
   int day;
   int month;
   int year;
   bool isLeap;
};

bool leap(int year){
  if (year%4 == 0 && year%400 != 0){
    return true;
  }
  return false;
}

struct day* nextDay(struct day* current){
   struct day* next = malloc(sizeof(struct day));
   next->day = current->day + 1;
   next->month = current->month;
   next->year = current->year;
   next->isLeap = current->isLeap;
   
   if ((next->day == 32 && next->month == 1) ||
      (next->day == 29 && next->month == 2 && next->isLeap == false) ||
      (next->day == 30 && next->month == 2) ||
      (next->day == 32 && next->month == 3) ||
      (next->day == 31 && next->month == 4) ||
      (next->day == 32 && next->month == 5) ||
      (next->day == 31 && next->month == 6) ||
      (next->day == 32 && next->month == 7) ||
      (next->day == 32 && next->month == 8) ||
      (next->day == 31 && next->month == 9) ||
      (next->day == 32 && next->month == 10) ||
      (next->day == 31 && next->month == 11)) {
      next->day = 1;
      next->month++;    
   } else if (next->day == 32 && next->month == 12) {
      next->day = 1;
      next->month = 1;
      next->year++;
      next->isLeap = leap(next->year);
   }
   free(current);
   return next;
}

//january 6, 1901 is first sunday of the time period.

int main(void){
   int count = 0;
   struct day* day = malloc(sizeof(struct day));
   day->day = 6;
   day->month = 1;
   day->year = 1901;
   
   do{
      for (int i = 0; i < 7; i++){
         day = nextDay(day);
      }
      if (day->day == 1 && day->year < 2001){
         count++;
      }
   } while (day->year < 2001);
   
   free(day);
   printf("%d\n",count);
}

