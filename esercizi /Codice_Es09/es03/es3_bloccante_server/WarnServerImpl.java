import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class WarnServerImpl extends UnicastRemoteObject implements WarnServer {
	private static final long serialVersionUID = 1L;
	WarnServerImpl() throws RemoteException {
	}
	public void WarnAt(int X, WarnClient c) throws RemoteException {
		System.out.println("server riceve richiesta a "+System.currentTimeMillis());
		System.out.println("server"+Thread.currentThread().getName()+" riceve richiesta a "+System.currentTimeMillis());
		try {
			// NB: cosi` il chiamante resta bloccato per tutto il tempo
			Thread.sleep(X*1000);
		} catch(InterruptedException e) {
		}
		System.out.println("server manda notifica a "+System.currentTimeMillis());
		c.notifyWarn();
	}
	public static void main(String[] Args) throws RemoteException {
		WarnServer s = new WarnServerImpl();
		Registry reg = LocateRegistry.createRegistry(1099);
		reg.rebind("WARNAT", s);
		System.out.println("server ready");
	}
}
