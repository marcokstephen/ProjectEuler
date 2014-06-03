#lang racket
;;runtime of ~22 seconds
(define min (inexact->exact (floor (sqrt 1020304050607080900))))
(define (check-number current)
  (define sqrnum (* current current))
  (define numstring (number->string sqrnum))
  (cond
    [(and (equal? (string-ref numstring 0) #\1)
          (equal? (string-ref numstring 2) #\2)
          (equal? (string-ref numstring 4) #\3)
          (equal? (string-ref numstring 6) #\4)
          (equal? (string-ref numstring 8) #\5)
          (equal? (string-ref numstring 10) #\6)
          (equal? (string-ref numstring 12) #\7)
          (equal? (string-ref numstring 14) #\8)
          (equal? (string-ref numstring 16) #\9)
          (equal? (string-ref numstring 18) #\0)) numstring]
    [else (check-number (+ 10 current))]))
(time (check-number min))
