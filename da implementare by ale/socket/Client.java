package socket;
// importo tutte le cose che c erano 

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


// tolgo extends thread
public class Client {
  //variabili   
  	
	Socket socket;
	ObjectOutputStream out;
	ObjectInputStream in;


    // ricopio tutti i metodi 

	// se ho un metodo con lo stesso nome della classe aggiungere un currenttime
	
	//client(){
	// tolgo gli argomenti client(int i, string s)
	// tolgo 		ilGioco=t;

		//long now=System.currentTimeMillis(); vedere se serve implementare un timer
	//	ricopio le altre funzioni
	//	mioNome="Giocatore_"+now;

	//	rnd=new Random();
	//}


    // quando sono al metodo run 
    //cambiare nome metodo run con exec()
	// per ogni sysyem. out mettere dopo n out.writeObject cosi da comunicare il cambio di messaggio
	void dormitina(int a) {
		int t=200+new Random().nextInt(a);
		try {
			Thread.sleep((long) t);
		} catch (InterruptedException e) {		}
	}

    public void exec()throws IOException{ // mettere throws IOException
        //variabili del metodo run 

        int attesa=250;
		try {
			InetAddress addr = InetAddress.getByName(null);
			Socket socket=new Socket(addr, 8999);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
            // implementare azioni del run 
			//mioStato=prossimoStato(-1); // stato iniziale
			//System.out.println(mioNome+" inizio in stato "+mioStato);
			out.writeObject("inizio");


			//out.writeObject(mioNome);
		//	out.writeObject(mioStato);			
			for(int j=0; j<10; j++) { // o while(true) 

				// da togliere il for(;;){ se prensente 
				// volendo se non ho il metodo dormitina posso crearlo io 
// lo metto come nuovo metodo ( guardare riga 41)

                   //funzioni 

		
				//dormitina(200); se c è il metodo dormitina allora lasciare cosi se no mettere questo 

	/*try {Thread.sleep(ThreadLocalRandom.current().nextInt(200,300));} catch (InterruptedException e) { }if(ThreadLocalRandom.current().nextBoolean()) { */



// la parte del writeObject va messa dopo il metodo dormitina ma non perforza nel exec se prima avevo un metodo che stampava e nel exec viene ripreso, il writeObject va messo in quel metodo dove c è il sysyem

			//	if(rnd.nextBoolean()) { // decide cosa fare
					// cambia stato
				//	mioStato=prossimoStato(mioStato);
			//		System.out.println(mioNome+" vado in stato "+mioStato);
			//		out.writeObject("cambiaStato");
			//		out.writeObject(mioNome);
			//		out.writeObject(mioStato);	
			//		dormitina(500);
			//	} else { // provo del cambio di stato 
					// promozione
		//			System.out.println(mioNome+" provo promozione ");
		//			out.writeObject("promozione");
		//			out.writeObject(mioNome);
		//			out.writeObject(attesa);
		// invece che sting mettere il tipo se è di tipo messaggio allora mettere messaggio
		// s=(String)in.ReadObkect); se il parametro che è cambiato è una stringa non fare parte del ultimo if
// system.out.print(ho letto + s) 




/*

// system.out.prtint<(inizio
//while
cerco di leggere
ci riesco 
out.writeObject("get");
				s=(String)in.readObject();
				System.out.println("Reader "+miaId+" ha letto "+s);
non ci riesco 

fallita
/*
  legge messaggio
					out.writeObject("read");
					out.writeObject(name);
					out.writeObject(timeout);
					msg=(Messaggio) in.readObject();
					if(msg==null) {
						System.out.println(name+" letto niente :-(");
					} else {
						System.out.println(name+" ho letto "+msg);					
					}
 */

// esempio di implementazione di fallimento 



// tutta la parte qui sotto non farla se la variabile è string
		//			esito=(boolean) in.readObject(); // se il parametro cambiato è in boolean
		//		if(esito) {
		//				dormitina(500);
			//			System.out.println(mioNome+" promozione OK!");
			//		} else {
			//			System.out.println(mioNome+" promozione fallita");
				//	}
			//	}
		//	}
			out.writeObject("end");
			out.close();
			in.close();
			socket.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}



	// inserire il main 

	public static void main(String args[])  {
		new Client().exec();
		System.out.println("Client: ohibo`, termina main");
	}

    
}

