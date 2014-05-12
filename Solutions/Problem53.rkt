#lang racket

(define (factorial n)
  (if (= n 0) 1 (* n (factorial (- n 1)))))

(define (choose n r)
  (/ (factorial n) (* (factorial r)(factorial (- n r)))))

(define (find-ans n r count)
  (cond
    [(> n 100) count]
    [(equal? n r) (find-ans (add1 n) 0 count)]
    [else (cond
            [(> (choose n r) 1000000) (find-ans n (add1 r) (add1 count))]
            [else (find-ans n (add1 r) count)])]))

(time (find-ans 1 0 0))