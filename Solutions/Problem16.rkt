#lang racket

(define num (expt 2 1000))

(define numlist (string->list(number->string num)))

(define (add-chars myList)
   (cond
      [(empty? myList) 0]
      [else (+ (- (char->integer(first myList)) 48) (add-chars (rest myList)))]))
      
(add-chars numlist)
