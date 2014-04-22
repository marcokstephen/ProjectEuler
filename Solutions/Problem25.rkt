#lang racket

(define bignum (expt 10 999))
(define (find-term fib1 fib2 count)
  (cond
    [(>= (+ fib1 fib2) bignum) count]
    [else (find-term fib2 (+ fib1 fib2) (+ count 1))]))
(find-term 1 1 3)
