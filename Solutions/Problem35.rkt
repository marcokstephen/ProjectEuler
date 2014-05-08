#lang racket

(define (list->num lon)
  (define (convert num-list output)
    (cond
      [(empty? num-list) output]
      [else (convert (rest num-list) (+ (* output 10) (- (char->integer (first num-list)) 48)))]))
  (convert lon 0))

(define (list-circle num)
  (define num-list (string->list (number->string num)))
  (cond
    [(>= num 100000)  (map (lambda (x) (list->num x)) (list num-list
      (list (second num-list) (third num-list) (fourth num-list) (fifth num-list) (sixth num-list) (first num-list))
      (list (third num-list) (fourth num-list) (fifth num-list) (sixth num-list) (first num-list) (second num-list))
      (list (fourth num-list) (fifth num-list) (sixth num-list) (first num-list) (second num-list) (third num-list))
      (list (fifth num-list) (sixth num-list) (first num-list) (second num-list) (third num-list) (fourth num-list))
      (list (sixth num-list) (first num-list) (second num-list) (third num-list) (fourth num-list) (fifth num-list))))]
    [(>= num 10000) (map (lambda (x) (list->num x)) (list num-list
      (list (second num-list) (third num-list) (fourth num-list) (fifth num-list) (first num-list))
      (list (third num-list) (fourth num-list) (fifth num-list) (first num-list) (second num-list))
      (list (fourth num-list) (fifth num-list) (first num-list) (second num-list) (third num-list))
      (list (fifth num-list) (first num-list) (second num-list) (third num-list) (fourth num-list))))]
    [(>= num 1000) (map (lambda (x) (list->num x)) (list num-list
      (list (second num-list) (third num-list) (fourth num-list) (first num-list))
      (list (third num-list) (fourth num-list) (first num-list) (second num-list))
      (list (fourth num-list) (first num-list) (second num-list) (third num-list))))]
    [(>= num 100) (map (lambda (x) (list->num x)) (list num-list
      (list (second num-list) (third num-list) (first num-list))
      (list (third num-list) (first num-list) (second num-list))))]
    [(>= num 10) (map (lambda (x) (list->num x)) (list num-list
      (list (second num-list) (first num-list))))]
    [else (list num)]))

(define (prime? num)
  (define (isPrime? number test max)
    (cond
      [(> test max) #t]
      [(equal? 0 (remainder number test)) #f]
      [else (isPrime? number (add1 test) max)]))
  (isPrime? num 2 (sqrt num)))

(define (test-all lon)
  (cond
    [(empty? lon) #t]
    [(prime? (first lon)) (test-all (rest lon))]
    [else #f]))

(define (main test count)
  (cond
    [(>= test 1000000) count]
    [(and (prime? test) (test-all (rest (list-circle test)))) (main (add1 test) (add1 count))]
    [else (main (add1 test) count)]))

(time (main 2 0))
