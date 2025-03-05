import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.NotBoundException;

public class WarnClientImpl extends UnicastRemoteObject implements WarnClient {
	private static final long serialVersionUID = 1L;
	WarnClientImpl() throws RemoteException{
		super();
	}
	public void notifyWarn() throws RemoteException {
		System.out.println("client riceve notifica a "+System.currentTimeMillis());
	}
	public static void main(String[] Args) throws RemoteException, NotBoundException {
		WarnClient c = new WarnClientImpl();
		Registry reg = LocateRegistry.getRegistry();
		WarnServer stub = (WarnServer) reg.lookup("WARNAT");
		stub.WarnAt(3, c);
		try{
			Thread.sleep(6000);
		} catch(InterruptedException e){}
		UnicastRemoteObject.unexportObject(c, true);
		System.out.println("client termina");
	}
}
