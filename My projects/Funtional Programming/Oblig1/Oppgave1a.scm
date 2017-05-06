

; EMNET: I    NF2810
; BRUKENAVN:  ABDULLIA
; OBLIG:      1A
; FRIST:      03.FEB.2017 KL. 14:00


; ********  1. Grunnleggende syntaks og semantikk i Scheme  ************


; a). 
(* (+ 4 2) 5)   ;->  Svaret er 30

; b). (* (+ 4 2) (5))  -> feil  tallet 5 trenger procedyre argument ellers 5 evalueres i seg selv.

;; c). (* (4 + 2) 5) -> feil den + operatoren må komme før 4 e.g (* (+ 4 2)5). Fordi Scheme bruker pre-fix notasjon.

; d). 
(define bar (/ 42 2))   ; bar -> 21  bar er deklarert eller bundet til 21, for 42/2.

; e). 
(- bar 11)   ; ->  Svaret er 10 selv om bar var 21, så blir det 21-11 = 10.

; f). 
(/ (* bar 3 4 1 ) bar) ;-> svaret er 12 bar var 21, så blir det 21*3*4*1 = 252/21 = 12.


(define bar (/ 42 2))
(/ (* bar 3 4 1) bar)  ; ->  12


; **************   2. Kontrollstrukturer og egendefinerte prosedyrer ***********************

;2.a

(or (= 1 2)
    "piff!"
    "paff!"
    (zero? (1 - 1)))
; synteksen til OR: (and ⟨e1⟩ ...... ⟨en⟩)
; Den retunerer  "piff" fordi OR er en special form som evaluerer argumentene
; en og en fra venstre til høyre. Hvis en uttrykk (expression) evalueres som sann verdi så 
; denne verdien retuneres som en verdi av OR og ingen andre argumenter evalueres.



(and (= 1 2)
     "piff!"
     "paff!"
     (zero? (1 - 1)))
; synteksen til AND: (and ⟨e1⟩ ...... ⟨en⟩)

; Den retunerer #f eller false. Fordi AND er også en special form som evaluerer argumentene
; en og en fra venstre til høyre. Hvis den finner eller evalurer en uttrykk (expression) som
; false, så er AND verdien false og resten av uttrykkene evalueres ikke.


(if (positive? 42)
    "poff!"
    (i-am-undefined))
; synteksen til if: (if ⟨predicate⟩ ⟨consequent⟩ ⟨alternative⟩)
; Den retunerer " poff". Fordi if er også en special form. 
; Hvis predikaten til if i dette tilfellet (positive? 42) evalueres som 
; sann så verdien til konsekvens retuneres("poff"), ellers alternative 
; retuneres hvis predikaten til if er testet usann.

;2. b

(define (SignCon x)
  (cond ((> x 0) 1)
        ((= x 0) 0)
        ((< x 0) (- 1))))

(define (SignIf x)
  (if (< x 0)
      -1
      (if (> x 0)
          1
          0)))

; 2. c
(define (sign x)
  (or (and (< x 0) -1)
      (and (> x 0) 1)
      0))


; *************  3. Rekursjon, iterasjon, og blokkstruktur  ************


; 3.a

(define (add1 x) (+ x 1))

(define (sub1 x) (- x 1))

;3.b
 
; Recursive prosess
(define (plus a b)
  (if (= a 0) b (add1 (plus (sub1 a) b))))

; ved bruk av Zero predikatet.
(define (plus a b)
  (if (zero? a) b (add1 (plus (sub1 a) b))))

;3.c

;Analysen: den rekursive prosedyren i oppgave b gir rekursive prosess i tilleg til 
; at den anvender seg selv  i egen definisjon eller caller seg selv.
; den har også basis tilfelle.

;Iterative prosess
;den gir iterativ prosess.
(define (plus a b)
  (if (= a 0) b  (plus (sub1 a) (add1 b))))

;3.d
;Svar og forklaring: Her definerer jeg "power-iter" som lokalt og internt da kan ikke brukes andre steder.
;Også danner det blokk struktur når det er definert internt.
;Jeg også forenklet hjelpe-prosodyren slik at det tar mindre argument. Nå variablene b og n er frie i definisjonen av "power-iter".

(define (power-close-to b n)
  (define (power-iter e)
    (if (> (expt b e) n)
        e
        (power-iter (+  e 1))))
  (power-iter 1))

;3.e


(define (fib n)
  (fib-iter 1 0 n))
(define (fib-iter a b count)
  (if (= count 0)
      b
      (fib-iter (+ a b) a (- count 1))))

; Den er ikke mulig å forenkle den interne definisjonen av "fib-iter" 
; FORDI hvis vi gjøre variablene til den interne definisjonen av "fib-iter"  frie 
; så oppdateres sine verdiene i hvert rekursive kall.

