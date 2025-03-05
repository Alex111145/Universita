package RMI;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class clientnuovo {
    ServerGioco gestoreGioco;

    // togliere  questo 
    	//Socket socket;
	//ObjectOutputStream out;
	//ObjectInputStream in;


    public void exec() {
		Registry reg;
		boolean esito;
		try {
			reg = LocateRegistry.getRegistry();
			gestoreGioco=	(ServerGioco)	reg.lookup("ServizioGioco"); // cambiare questi nomi
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
			System.exit(0);
		}
            // ricopiare le funzioni del metodo run della soluzione della concorrenza
    


    }

// mettere il main 

            public static void main(String args[]) throws RemoteException {
                new clientnuovo().exec();
                System.out.println("Client: ohibo`, termina main");
            }
        }




