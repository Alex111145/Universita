//1. individuare la classe condivisa ( non è il main)
//mettere tutti i suoi metodi  ----- > synchronized

//trovare i due metodi che vengono nominati nel testo 
//in quello che permette l entrata e quello che permette il cambio di stato./ passaggip 

//in quello d entrata 





// se il metodo dove dovrei mettere il wait non ha un argomento gia passato allora fare cosi : 

/* 
public synchronized Resource getB() {
	
	Resource r;
	if(listB.isEmpty()) {
		return (Resource) null;
	} else {
		r=listB.remove(0);
		printout();
	
		
			}	return r;
	}

*/
//1. trovare l if e mettere un while che controlla se la risorsa è disponibile tenerlo e cancellare il resto del codice
//2. mettere il wait dentro l if
// 3. mettere l eventuale else come eseguibile sempre 


// aspetta fino anche al infinito
 public synchronized Resource getB() {
/*		Resource r;
		while(listB.size()==0) {
			try { // wait al interno dell while
				wait();
			} catch (InterruptedException e) { }
		}
		r=listB.remove(0); // l else viene eseguito sempre
		printout();
		return r;
	}
 */




// questo metodo aspetta fino ad un certo punto e poi va anche se non disponile 
public synchronized boolean promozione(String nome, int t) { // aggiungere int t nel argomento 
	// quando aggiungo int t nel argomento ricordarsi che quando questo metodo viene usato in un altra parte
	// va messo anche tipo : .promozione(ciao,2500) che è il valore di t
	
	
	
	
	
	//if(iGiocatori.containsKey(nome)) {
			//int stato=iGiocatori.get(nome);
			//int count;
			long start=System.currentTimeMillis();
			long toWait=t; // aggiungere questa variabile
			for(;;) { // implementare questo for solo se per il cambio di stato c è bisogno della conferma di qualcun altro ( esempio : giocatore fa la mossa e ha bisogno della cconferma se la mossa sia valida da parte dell arbitro)
				// se no non metterlo 
			//	count=quantiInStato(stato);
			//	System.out.println("Tavolo: trovati "+count+" giocatori in stato "+stato+" per "+nome);
			//	if(condizioneProgressioneOK(count, stato)) {
			//		return true;
			//	}

			// trasformare l if in un while mettere tutta la funzione de sempre dentro al while anche il wait
		 	while(condizione  /* di  ( condizione per farlo funzionare*/){
				// funzione del programma (ricopiare le funzioni)

				toWait=start+t-System.currentTimeMillis();
				if(toWait>0) {
					try { wait(t); } catch (InterruptedException e) { }


			}
			
			} 
			//copia il codice programma dove da errore
			// caso non funziona if(condizione di fermo)
			if(condizione non){
				//copia il codice programma dove da errore
			}




// in quello di cambio di stato/ passaggio 


public synchronized void cambiaStato(String nome, int stato) {
	//if(iGiocatori.containsKey(nome)) {
	//	iGiocatori.put(nome, stato);
	//}

	// questo va dopo un messaggio del tipo
	//System.out.println("PostOffice:  memorizzato messaggio  "+ m);
		//}
		notifyAll(); // aggiungere questo		
}
