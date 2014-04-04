#lang racket

(define regList (rest (build-list 101 values)))
(define sqrList (rest (build-list 101 (lambda (x) (* x x)))))

(define (addList myList)
   (cond
      [(empty? myList) 0]
      [else (+ (first myList) (addList (rest myList)))]))
      
(define sumSquares (addList sqrList))
(define sqrReg (expt (addList regList) 2))

(- sqrReg sumSquares)
