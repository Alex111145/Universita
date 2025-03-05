Questa soluzione prevede che dopo aver pensato, il giocatore si blocchi in attesa del proprio turno.
Non potendo modificate TavoloGioco, si introduce una nuova classe Turno che ha il solo scopo di gestire i turni a questo scopo, la classe Turno mette a disposizione due metodi: aspettaTurno(identificatore) che provoca l'attesa del proprio turno (bloccante se chiamata quando di turno e` l'altro giocatore) e cambiaTurno() che rende di turno l'altro giocatore.

NB: era possibile ottenere la sinconizzazione anche attraverso una coppia di semafori: ciascun giocatore fa acquire su un semaforo e release sull'altro. In questo caso entrambi i semafori devonoi essere creati e inizializzati dal main e passati ai giocatori.

