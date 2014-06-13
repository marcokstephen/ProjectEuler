#lang racket
;;bad, takes about 5 minutes

(require math/number-theory)
(define MAX 1000000)

(define tempChain '())

(define (construct-chain num)
  (define (amic-chain current)
    (define next (foldr + (- 0 current) (divisors current)))
    (cond
      [(or (> next MAX) (prime? next) (equal? current 1) (member current tempChain)) (cons #f empty)]
      [(equal? next num) (cons current empty)]
      [else (set! tempChain (append (list current) tempChain)) (cons current (amic-chain next))]))
  (amic-chain num))

(define (find-longest-chain currentNum maxLen maxNum)
  (set! tempChain '())
  (define currentChain (construct-chain currentNum))
  (define currentLen (length currentChain))
  (cond
    [(equal? currentNum MAX) maxNum]
    [(and (> currentLen maxLen) (not (member #f currentChain))) (find-longest-chain (add1 currentNum) currentLen currentNum)]
    [else (find-longest-chain (add1 currentNum) maxLen maxNum)]))

(foldr min MAX (construct-chain (find-longest-chain 1 0 0)))