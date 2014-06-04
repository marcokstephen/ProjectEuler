#lang racket
(require math/number-theory)
(define primeList (next-primes 1 66))

(define (rad n)
  (cond
    [(equal? n 1) 1]
    [else (foldr * 1 (prime-divisors n))]))

(define dictionary (rest (build-list 100001 (lambda (x) (cons x (cons (rad x) empty))))))

(define (myCompare listA listB)
  (define keyA (second listA))
  (define keyB (second listB))
  (cond
    [(> keyA keyB) #f]
    [else #t]))

(define sorted (sort dictionary myCompare))

(define (getValue position list)
  (define (get-value current position list)
    (cond
      [(equal? current position) (first list)]
      [else (get-value (add1 current) position (rest list))]))
  (get-value 1 position list))

(first (getValue 9999 sorted))
