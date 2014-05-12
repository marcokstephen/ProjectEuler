#lang racket

(define (number->list num)
  (map (lambda (x) (- (char->integer x) 48)) (string->list (number->string num))))

(define (reverse-num num)
  (define numList (number->list num))
  (define (constructReverse numberList answer position)
    (cond
      [(empty? numberList) answer]
      [else (constructReverse (rest numberList) (+ answer (* (expt 10 position) (first numberList))) (add1 position))]))
  (constructReverse numList 0 0))

(define (palindrome? num)
  (cond
    [(equal? num (reverse-num num)) #t]
    [else #f]))

(define (lychrel? num count)
  (cond
    [(>= count 50) #t]
    [(palindrome? (+ num (reverse-num num))) #f]
    [else (lychrel? (+ num (reverse-num num)) (add1 count))]))

(define (find-ans num count)
  (cond
    [(>= num 10000) count]
    [(lychrel? num 1) (find-ans (add1 num) (add1 count))]
    [else (find-ans (add1 num) count)]))

(time (find-ans 1 0))