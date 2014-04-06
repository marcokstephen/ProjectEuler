#lang racket

(define list (rest (build-list 101 values)))

(define (factorial myList)
  (cond
    [(empty? myList) 1]
    [else (* (first myList) (factorial (rest myList)))]))

(define product (factorial list))
(define productList (string->list (number->string product)))

(define (sumProductList prodList)
  (cond
    [(empty? prodList) 0]
    [else (+ (- (char->integer (first prodList)) 48) (sumProductList (rest prodList)))]))

(sumProductList productList)