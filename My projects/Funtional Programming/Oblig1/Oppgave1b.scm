

; EMNET: INF2810
; BRUKENAVN:  ABDULLIA
; OBLIG:      1B
; FRIST:      24.FEB.2017 KL. 15:00


; ********  1. Par og lister  ************


; Oppgavene fra 1.a til  1.e er vedlagt egenfil.


; f). (0 42 #t bar)  svaret er: (car(cdr '(0 42 #t bar)))

; g). ((0 42) (#t bar)) svaret er: (cdr(car'((0 42) (#t bar))))

; h). ((0) (42 #t) (bar)) svaret: (car(car(cdr'((0) (42 #t) (bar)))))

; i). oppgave (g) ved bruk av prosedyren cons og bare ved bruk av list.
    
; svaret er: (cons (list 0 42) (list'(#t bar)))



; **************   2. Rekursjon over lister og høyereordens prosedyrer ***********************

;2.a



(define (length2 items)
  (define (lenTale llist returnValue)
    (if (null? llist)
        returnValue
        (lenTale (cdr llist)
                 (+  returnValue 1))))
  (lenTale items 0))

;2.b

(define (reduce-reverse proc init items)
  (define (reverse-iter in init)
    (if (null? in)
        init
        (reverse-iter (cdr in)
                      (proc (car in) init))))
  (reverse-iter items init))

; prosedyredefinisjonen min er halerekursive fordi det er den
; interne hjelpeprosedyren nemlig "reverse-iter" som gjør rekursjonen.
; Når vi kaller prosedyren igjen, kaller vi i haleposissionen. 
; Så etter det kallet det er ikke noe annet å gjøre i prosedyrekroppen.

; Dette er iterative process.


;2.c

(define (all?  pred items)
  (cond ((null? items)
         #t)
        ((pred (car items))
         (all? pred (cdr items)))
          
        (else
         #f)))


;Vis også hvordan all? kan kalles med en anonym prosedyre 
;(lambda-uttrykk) definert slik at all? re- turnerer #f dersom noen av tallene i listeargumentet er høyere enn 10. 
;Kalleksempler (der du skal fylle inn uttrykket for ????):

(all?  (lambda (x)
      
         (< x 10)) '(1 2 3 4 50))

;2.d

(define (nth indeks items)
  (define (nthhelp pos items)
    (if(= pos indeks)
       (car items)
       (nthhelp (+ pos 1) (cdr items))))
  (nthhelp 0 items))

;2.e

(define (where indeks items)
  (define (wherehelp pos items)
    (if(= (car items) indeks)
       pos
       (wherehelp (+ pos 1) (cdr items))))
  (wherehelp 0 items))

;2.f

(define (map2 pre ll1 ll2)
  (if (or (null? ll1) (null? ll2))
      '()
      (cons( pre (car ll1) (car ll2))
           (map2
            pre (cdr ll1) (cdr ll2)))))


;2.g

;ved bruk av lambda og map2 som er definert 2.f

(map2 (lambda (x y) (/ (+ x y) 2)) '(1 2 3 4) '(3 4 5))

;en vanlig prosedyret som gjør det sammet uten lambda og map2

(define (map2avg pre ll1 ll2)
  (if (or (null? ll1) (null? ll2))
      '()
      (cons(pre( + (car ll1) (car ll2)))
           (map2avg
            pre (cdr ll1) (cdr ll2)))))


;2.h
(define (both? pre)
  (lambda (x y)
    (and (pre x)(pre y))))

;2.i

(define (self pre)
  (lambda (x)
    (pre x x)))





