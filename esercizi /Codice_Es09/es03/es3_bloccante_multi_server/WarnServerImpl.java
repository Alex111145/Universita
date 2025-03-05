import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class WarnServerImpl extends UnicastRemoteObject implements WarnServer {
	private static final long serialVersionUID = 1L;
	WarnServerImpl() throws RemoteException {
		super();
	}
	public void WarnAt(int X, WarnClient c) throws RemoteException {
		// bloccante
		// blocca tutti i client?
		System.out.println("server "+Thread.currentThread().getName()+" riceve richiesta da "+c+" per "+X+" sec.");
		int t=0;
		while(t<X) {
			try {
				Thread.sleep(1000);
			} catch(InterruptedException e) { }
			t++;
		}
		c.notifyWarn();
	}
	public static void main(String[] Args) throws RemoteException {
		WarnServer s = new WarnServerImpl();
		Registry reg = LocateRegistry.createRegistry(1099);
		reg.rebind("WARNAT", s);
		System.out.println("Warning server registered");
	}
}
