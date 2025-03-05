package RMI;
import java.rmi.Remote;
import java.rmi.RemoteException;


public interface servergioco extends Remote { // metto interface

    // copiare tutti i metodi syncronize che ci sono nella classe condivisa cioe quella con il wait e notify quando ricopio togliere syncronize e controllare siano public 
	public void iniziaGioco(String nome, int stato) throws RemoteException;
	public void cambiaStato(String nome, int stato) throws RemoteException;
	public boolean promozione(String nome, int t) throws RemoteException; // attento che quando faro implememnt di questo metodo devo ricopiare tutto il codice di quel metodo dalla classe codivisa 
}