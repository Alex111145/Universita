import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class WarnClientImpl extends UnicastRemoteObject implements WarnClient{
	private static final long serialVersionUID = 1L;
	protected WarnClientImpl() throws RemoteException {
		super();
	}
	public void notifyMe() throws RemoteException {
		System.out.println("warning received from server!");
	}
	
	void exec(int id) throws RemoteException {
		Registry reg=null;
		try {
			reg=LocateRegistry.getRegistry(1099);
		} catch (RemoteException e) {
			System.err.println("could not find the registry");
			System.exit(0);
		}
		WarnServer laSveglia=null;
		try {
			laSveglia = (WarnServer) reg.lookup("WarnAt");
		} catch (RemoteException | NotBoundException e) {
			System.err.println("could not find the service");
			System.exit(0);
		}
		System.out.println("client_"+id+": sending warnAt request");
		laSveglia.warnAt(6, this);
		for(int i=0; i<10; i++) {
			System.out.println("client_"+id+": doing something");
			try {
				Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 1500));
			} catch (InterruptedException e) { }
		}
		System.out.println("Terminating");
		UnicastRemoteObject.unexportObject(this, true);		
	}
	public static void main(String[] args) {
		Random rnd=new Random();
		try {
			new WarnClientImpl().exec(rnd.nextInt(1, 10000));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
