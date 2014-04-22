#lang racket

(define (find-sum num sum)
  (cond
    [(= 1000 num) (+ sum (expt num num))]
    [else (find-sum (add1 num) (+ sum (expt num num)))]))
(time (find-sum 1 0))