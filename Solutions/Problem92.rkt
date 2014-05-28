#lang racket
;;bad, about 2 minutes
(define (sum-square-digit num)
  (define (num->list number)
  (cond
    [(equal? number 0) empty]
    [else (cons (remainder number 10) (num->list (floor (/ number 10))))]))
  (define sqrlist (map (lambda (x) (* x x)) (num->list num)))
  (foldr + 0 sqrlist))

(define (ends-at-eightynine number)
  (define next (sum-square-digit number))
  (cond
    [(equal? next 89) #t]
    [(equal? next 1) #f]
    [else (ends-at-eightynine next)]))

(define (count-number num count)
  (cond
    [(equal? num 10000000) count]
    [(ends-at-eightynine num) (count-number (add1 num) (add1 count))]
    [else (count-number (add1 num) count)]))

(time (count-number 2 0))
