
Questa soluzione prevede l'uso degli interrupt. Ogni volta che un giocatore fa una mossa, manda un interrupt all'altro giocatore. La ricezione di un interrupt indica che si puo` muovere.
NB: affinche' un thread giocatore possa mandare un interrupt al thread dell'altro giocatore deve avere il riferimento a tale thread. Pertanto, poccorre che il main che crea io thread giocatori fornisca questi riferimenti (col metodo setAvversario nell asoluzione proposta).
NB: un giocatore continua a pensare fino a quando non riceve l'interrupt.

