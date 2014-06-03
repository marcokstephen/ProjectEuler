#lang racket

(define (count-integers num-digits)
  (define (count-ints current count)
    (define digitlen (string-length (number->string current)))
    (define exptlen (string-length (number->string (expt current num-digits))))
    (cond
      [(> exptlen num-digits) count]
      [(equal? exptlen num-digits) (count-ints (add1 current) (add1 count))]
      [else (count-ints (add1 current) count)]))
  (count-ints 1 0))

(define (iterate-count current max)
  (cond
    [(equal? current (add1 max)) empty]
    [else (cons (count-integers current) (iterate-count (add1 current) max))]))

(foldr + 0 (iterate-count 1 30))