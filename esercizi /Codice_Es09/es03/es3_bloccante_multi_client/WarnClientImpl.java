import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.Random;
import java.rmi.NoSuchObjectException;
import java.rmi.NotBoundException;

public class WarnClientImpl extends Thread implements WarnClient {
	boolean waitingWarning=false;
	int myId;
	static int tCount=0;
	WarnClientImpl(int i){
		tCount++;
		myId=i;
		this.start();
	}
	public static int threadCount() {
		return tCount;
	}
	public void notifyWarn() throws RemoteException {
		Date now = new Date();
		System.out.println("client_"+myId+" riceve notifica @"+ now);
		waitingWarning=false;
	}
	public void run() {
		WarnServer stub = null;
		WarnClient remotCli;
		Registry reg;
		int secToWait=new Random().nextInt(10);
		try {
			remotCli = (WarnClient) UnicastRemoteObject.exportObject(this, 2033);
			reg = LocateRegistry.getRegistry();
			stub = (WarnServer) reg.lookup("WARNAT");
			Date now=new Date();
			System.out.println("client_"+myId+" manda richiesta per attesa di "+secToWait+" sec. @"+ now);
			waitingWarning=true;
			stub.WarnAt(secToWait, remotCli);
		} catch (RemoteException | NotBoundException e1) {	}
		while(waitingWarning) {
			System.out.println("Client_"+myId+" doing something");
			try{
				Thread.sleep(500);
			} catch(InterruptedException e){}			
		}
		tCount--;
		try {
			UnicastRemoteObject.unexportObject(this, true);
		} catch (NoSuchObjectException e) {	e.printStackTrace(); }
		System.out.println("client_"+myId+" termina");
	}
}

