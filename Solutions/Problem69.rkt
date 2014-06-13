#lang racket
(require math/number-theory)

(define (find-max num maxValue maxNum)
  (define tot (totient num))
  (cond
    [(equal? num 1000001) maxNum]
    [(> (/ num tot) maxValue) (find-max (add1 num) (/ num tot) num)]
    [else (find-max (add1 num) maxValue maxNum)]))

(time (find-max 2 0 0))
;;about 40 seconds