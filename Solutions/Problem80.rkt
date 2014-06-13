#lang racket
(require math/bigfloat)

(define (sum-list num)
  (define (get-first-hundred num)
    (bf-precision 350)
    (define substringList (string-split (bigfloat->string (bfsqrt (bf num))) "."))
    (substring (string-append (first substringList) (second substringList)) 0 100))
  (define numList (map (lambda (x) (- (char->integer x) 48)) (string->list (get-first-hundred num))))
  (foldr + 0 numList))

(time (foldr + 0 (map sum-list (filter (lambda (x) (inexact? (sqrt x))) (range 1 100)))))