#lang racket
(define (number->list num)
  (map (lambda (x) (- (char->integer x) 48)) (string->list (number->string num))))

(define (equal-l? lon1 lon2)
  (equal? (sort lon1 <) (sort lon2 <)))

(define (test-multiples num)
  (define basecase (number->list num))
  (cond
    [(and (equal-l? basecase (number->list (* 2 num))) (equal-l? basecase (number->list (* 3 num)))
          (equal-l? basecase (number->list (* 4 num))) (equal-l? basecase (number->list (* 5 num)))
          (equal-l? basecase (number->list (* 6 num)))) num]
    [else (test-multiples (add1 num))]))

(time (test-multiples 1))