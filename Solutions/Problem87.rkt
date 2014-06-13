#lang racket
;;7~ seconds

(require math/number-theory)
(define MAX 50000000)
(define maxPrime (ceiling (sqrt MAX)))

(define (construct-prime-list limit)
  (define (create-list current limit)
    (define next (next-prime current))
    (cond
      [(> next limit) empty]
      [else (cons next (create-list next limit))]))
  (create-list 1 limit))

(define primeList (construct-prime-list maxPrime))

(define (create-power-list lop1 lop2 lop3)
  (cond
    [(empty? lop1) (create-power-list primeList (rest lop2) lop3)]
    [(empty? lop2) (create-power-list primeList primeList (rest lop3))]
    [(empty? lop3) empty]
    [else (define sum (+ (expt (first lop1) 2) (expt (first lop2) 3) (expt (first lop3) 4)))
     (cond
       [(> sum MAX) (cond
                      [(> (expt (first lop3) 4) MAX) empty]
                      [(> (expt (first lop2) 3) MAX) (create-power-list primeList primeList (rest lop3))]
                      [(> (expt (first lop1) 2) MAX) (create-power-list primeList (rest lop2) lop3)]
                      [else (create-power-list (rest lop1) lop2 lop3)])]
       [else (cons sum (create-power-list (rest lop1) lop2 lop3))])]))

(time (length (remove-duplicates (create-power-list primeList primeList primeList))))