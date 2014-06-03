#lang racket

;;takes roughly 75 seconds to generate primes, 0.3s to find answer after primes are generated

(define (factorial n)
  (cond
    [(equal? n 0) 1]
    [else (* n (factorial (sub1 n)))]))

(define (choose n r)
  (/ (factorial n) (* (factorial r) (factorial (- n r)))))

(define (cons-row num)
  (define (construct-list current max)
    (cond
      [(equal? current max) (cons 1 empty)]
      [else (cons (choose max current) (construct-list (add1 current) max))]))
  (construct-list 0 (sub1 num)))

(define (list-uniques min max-row)
  (define (construct-list current max)
    (cond
      [(equal? current max) (cons-row current)]
      [else (append (cons-row current) (construct-list (add1 current) max))]))
  (remove-duplicates (construct-list min max-row)))

(define (prime? num)
  (define max (sqrt num))
  (define (check-prime current)
    (cond
      [(> current max) #t]
      [(equal? (remainder num current) 0) #f]
      [else (check-prime (add1 current))]))
  (check-prime 2))

(define (next-prime num)
  (cond
    [(prime? (add1 num)) (add1 num)]
    [else (next-prime (add1 num))]))

(define (construct-primes current max)
  (define nextPrime (next-prime current))
  (cond
    [(> nextPrime max) empty]
    [else (cons nextPrime (construct-primes nextPrime max))]))

(define sqrPrimeList (map (lambda (x) (* x x)) (construct-primes 1 4015446))) ;;this is equal to (sqrt (max distinct-list))

(define (squarefree? num)
  (define max (sqrt num))
  (define (testPrimeList num lop)
    (cond
      [(or (> (first lop) num) (empty? lop)) #t]
      [(equal? 0 (remainder num (first lop))) #f]
      [else (testPrimeList num (rest lop))]))
  (testPrimeList num sqrPrimeList))

(time (foldr + 0 (filter squarefree? (list-uniques 1 48)))) ;;no squarefree numbers from 49 - 51
