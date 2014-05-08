#lang racket

(define (palindrome? num)
  (define reversed-num (string->number (list->string (reverse (string->list (number->string num))))))
  (cond
    [(equal? reversed-num num) #t]
    [else #f]))

(define (convert-base num)
  (define (to-base-two dec sum posn)
    (cond
      [(equal? dec 0) sum]
      [(equal? 0 (remainder dec 2)) (to-base-two (/ dec 2) sum (add1 posn))]
      [else (to-base-two (floor (/ dec 2)) (+ sum (expt 10 posn)) (add1 posn))]))
  (to-base-two num 0 0))

(define (find-sum current-num sum)
  (cond
    [(equal? current-num 1000000) sum]
    [(and (palindrome? current-num) (palindrome? (convert-base current-num))) (find-sum (add1 current-num) (+ sum current-num))]
    [else (find-sum (add1 current-num) sum)]))

(find-sum 1 0)