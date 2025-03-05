import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {
	public static void main(String args[]) throws RemoteException {
		NewsServiceImpl obj = new NewsServiceImpl();
		Registry registro = LocateRegistry.createRegistry(1099);
		registro.rebind("NEWS", obj);
		new NewsUpdater(obj).start();
		System.out.println("Server ready");
	}
}