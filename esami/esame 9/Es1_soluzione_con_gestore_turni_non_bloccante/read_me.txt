In questa soluzione si una una classe Turno per gestire i turni, ma, non volendo bloccare il giocatore, l'attesa del turno viene demandata a un thread Attendente.
NB: il giocatore smette di pensare solo quando vede che e` il proprio turno, attraverso il flag settato dall'Attendente.
s
NB: il fatto che la simulazione del periodo in cui il giocatore penbsa sia fatta con sleep anziche' con un busy cycle e` irrilevante.
