package socket;
import java.io.*;
import java.net.Socket;

public class SlaveThread extends Thread {
	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;

 Tavolo (A) ilGioco (C);
// DO UN ALTRO NOME ALLA CLASSE CHE HA WAIT

// CAMBIARE TUTTE LE VOLTE CHE TROVO ilGioco con il nome inventato (C) della classe che ha wait

	protected SlaveThread(Socket s, Tavolo tavolo) throws IOException  { // qua devi togliere il commento
		socket = s;
		out = new ObjectOutputStream(s.getOutputStream());
		in = new ObjectInputStream(s.getInputStream());
		ilGioco(C)=tavolo(b);
        // C = B 
	}

	public void run() { 
		String command;
	// importare tutte le variabili che utilizzo nelle funzioni commads.equal ( le funzioni le trovo nel client)
		try {
			while (true) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {}
				command = (String) in.readObject();
				if(command.equals("end")) {
					break;
				}
				if(command.equals("inizio")) { // questi sono i comandi che vengono mandati dal client li trovo nel client

                    // CAMBIARE LE FUNZIONI DELLA FUNZIONE IN BASE A COSA DEVE FARE IL METODO 

					// salvo il cast delle variabili 
					// eseguo il metodo con lo stesso nome
					// non metto nessun system.out


					// CI SONO DEI CASI IN CUI SI DA COSI:
					//    out.writeObject(cas.newClient()); // QUESTA FUNZIONE NEL CLIENT RESTITUISCE UNA COSA CHE NON MI INTERESSA ( NON È LA FUNZIONE INERENTE ALLA FUNZIONE DOVE HO MESSO IL WAIT O NOTIFY)
					
// L ALTRA È QUESTA
//msg=(Messaggio)in.readObject();
//laPosta (C).put(msg);





					nome=(String)in.readObject();  //servono per leggere in input, devo farne tanti quanti i paramentri passati nel metodo iniziagioco
					stato=(int)in.readObject();// servono per prendere in input i valori passati 

					//ilGioco.iniziaGioco(nome, stato); // questo è l azione che viene fatta dopo il sistem out print nel client
				}
				if(command.equals("cambiaStato")) { // cambiare il messaggio di ritorno in base a quello che ritorna il metodo

                    // CAMBIO DI STATO 

				//	nome=(String)in.readObject();
				//	stato=(int)in.readObject();
				//	ilGioco.cambiaStato(nome, stato);
				}
				if(command.equals("promozione")) { // cambiare il messaggio di ritorno in base a quello che ritorna il metodo

                    // CAMBIA TUTTO 

					//nome=(String)in.readObject();
					//attesa=(int)in.readObject();
					//esito = ilGioco.promozione(nome, attesa);
					//out.writeObject(esito);
				}
				// possono essere anche piu o meno if dipende dalle funzioni 
			}
		} catch (IOException | ClassNotFoundException e) {
			System.err.println("IO Exception ");
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				System.err.println("Socket not closed");
			}
		}
	}
}
