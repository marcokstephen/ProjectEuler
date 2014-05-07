#lang racket

(define (build-value-list a b)
  (cond
    [(and (equal? a 100) (equal? b 100)) (cons (expt a b) empty)]
    [(< b 100) (cons (expt a b) (build-value-list a (add1 b)))]
    [(equal? b 100) (cons (expt a b) (build-value-list (add1 a) 2))]))

(length (remove-duplicates (build-value-list 2 2)))