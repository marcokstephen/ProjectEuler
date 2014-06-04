#lang racket

(define (prime? num)
  (define max (sqrt num))
  (define (test-prime current)
    (cond
      [(> current max) #t]
      [(equal? 0 (remainder num current)) #f]
      [else (test-prime (add1 current))]))
  (test-prime 2))

(define (next-prime current)
  (cond
    [(prime? (add1 current)) (add1 current)]
    [else (next-prime (add1 current))]))

(define (construct-prime-list max)
  (define (construct-list current)
    (cond
      [(>= current (add1 max)) empty]
      [else (cons current (construct-list (next-prime current)))]))
  (construct-list 2))

(define primeList (construct-prime-list 317))

;;does not work for value of n = 1
(define (rad n)
  (define (list-prime-factors num)
    (define (list-pf number pList)
      (define max (sqrt num))
      (cond
        [(equal? number 1) empty]
        [(> (first pList) max) (cons number empty)] ;;number is prime
        [(equal? 0 (remainder number (first pList))) (cons (first pList) (list-pf (/ number (first pList)) pList))]
        [else (list-pf number (rest pList))]))
    (list-pf num primeList))
  (cond
    [(equal? n 1) 1]
    [else (foldr * 1 (remove-duplicates (list-prime-factors n)))]))
 
(define dictionary (rest (build-list 100001 (lambda (x) (cons x (cons (rad x) empty))))))

(define (myCompare listA listB)
  (define keyA (second listA))
  (define keyB (second listB))
  (cond
    [(> keyA keyB) #f]
    [else #t]))

(define sorted (sort dictionary myCompare))

(define (getValue position list)
  (define (get-value current position list)
    (cond
      [(equal? current position) (first list)]
      [else (get-value (add1 current) position (rest list))]))
  (get-value 1 position list))

(printf "Value 9999: ")
(getValue 9999 sorted)
(printf "Value 10003: ")
(getValue 10003 sorted)
(printf "By working backwards from position 10003, you can see that E(10000) = position 9999 in my list (my list does not sort keys, only values)")
(printf "\n\nFinal answer: ")
(first (getValue 9999 sorted))
