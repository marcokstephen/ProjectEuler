#lang racket

;; I realised that the problem could be written as a quadratic diophantine equation

;; equations for find-total and find-blue were taken from WolframAlpha as solutions
;; to the QDE, then iterated until total exceeded one trillion

(define bigTotal 1000000000000)

(define (find-total n)
  (/ (+ (- 0 (expt (- 3 (* 2 (sqrt 2))) n))
        (* (sqrt 2) (expt (- 3 (* 2 (sqrt 2))) n))
        (- 0 (expt (+ 3 (* 2 (sqrt 2))) n))
        (* (sqrt 2) (expt (+ 3 (* 2 (sqrt 2))) n))
        2)
     4))

(define (find-blue n)
  (exact-round (/ (+ (* 2 (expt (- 3 (* 2 (sqrt 2))) n))
        (* (sqrt 2) (expt (- 3 (* 2 (sqrt 2))) n))
        (* 2 (expt (+ 3 (* 2 (sqrt 2))) n))
        (- 0 (* (sqrt 2) (expt (+ 3 (* 2 (sqrt 2))) n)))
        4)
     8)))

(define (find-max n)
  (define current-total (find-total n))
  (cond
    [(> current-total bigTotal) (find-blue n)]
    [else (find-max (add1 n))]))

(time (find-max 3))
