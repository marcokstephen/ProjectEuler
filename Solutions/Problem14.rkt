#lang racket

(define (collatz num)
   (cond
      [(equal? num 1) (cons 1 empty)]
      [(even? num) (cons num (collatz (/ num 2)))]
      [else (cons num (collatz (+ (* 3 num) 1)))]))
      
(define (collatz-length num)
   (length (collatz num)))
         
(define alist (rest (build-list 1000001 values)))

(define (find-max myMax myList num)
   (define clen (collatz-length (first myList)))
   (cond
      [(empty? (rest myList))
         (cond
            [(> clen myMax) (first myList)]
            [else num])]
      [(> clen myMax) (find-max clen (rest myList) (first myList))]
      [else (find-max myMax (rest myList) num)]))
      
(find-max 0 alist 0)
