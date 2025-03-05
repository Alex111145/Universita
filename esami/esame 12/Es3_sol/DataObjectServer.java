import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

public class DataObjectServer extends UnicastRemoteObject implements DataObjectInterface {
	private static final int SERVER_PORT = 1099;
	private static final long serialVersionUID = 1L;
	DataObject data;
	Random rnd;
	protected DataObjectServer() throws RemoteException {
		super();
		rnd=new Random();
		data=new DataObject();
	}

	public String get() throws RemoteException {
		return data.get();
	}
	private void exec() {
		try {
			Registry registry = LocateRegistry.createRegistry(SERVER_PORT);
			registry.rebind("DataObjectServer", this);
			System.out.println("server ready");
		} catch (RemoteException e) {
			System.out.println("Server could not start");
			System.exit(0);
		}
		boolean decision;
		while(true) {
			decision=rnd.nextBoolean();
			if(decision) {
				data.set("info_"+rnd.nextInt(1000));
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {		}
		}

	}
	public static void main(String[] args) throws RemoteException {
		new DataObjectServer().exec();
	}


}
