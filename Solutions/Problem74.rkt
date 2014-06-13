#lang racket
;;very bad, quite slow (several minutes)
(require math/number-theory)

(define tempList '())

(define (sum-digit-factorial num)
  (define digitList (map (lambda (x) (- (char->integer x) 48))
                         (string->list (number->string num))))
  (foldr + 0 (map factorial digitList)))

(define (construct-list number)
  (set! tempList '())
  (define (cons-list number)
    (cond
      [(member number tempList) empty]
      [else (set! tempList (cons number tempList)) (cons number (cons-list (sum-digit-factorial number)))]))
  (cons-list number))

(define (count-total num total)
  (cond
    [(equal? num 1000000) total]
    [else (define listLength (length (construct-list num)))
          (cond
            [(equal? listLength 60) (count-total (add1 num) (add1 total))]
            [else (count-total (add1 num) total)])]))
(count-total 1 0)