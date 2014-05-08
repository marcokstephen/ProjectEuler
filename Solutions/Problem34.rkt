;;not a "complete" solution -- no mathematical justification for the upper bound of 10 million
;;therefore (though correct) it is essentially a guess.

#lang racket

(define (factorial number)
  (cond
    [(equal? 0 number) 1]
    [else (* number (factorial (sub1 number)))]))

(define (sum-digit-factorial number)
  (define num-char-list (string->list (number->string number)))
  (define factorial-list (map (lambda (x) (factorial (- (char->integer x) 48))) num-char-list))
  (foldr + 0 factorial-list))

(define (curious? number)
  (cond
    [(equal? number (sum-digit-factorial number)) #t]
    [else #f]))

(define (find-sum current sum)
  (cond
    [(equal? current 10000000) sum]
    [(curious? current) (find-sum (add1 current) (+ sum current))]
    [else (find-sum (add1 current) sum)]))

(find-sum 3 0)