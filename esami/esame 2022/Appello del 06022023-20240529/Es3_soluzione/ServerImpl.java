import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerImpl extends UnicastRemoteObject implements DepositoInterface{
	public static final long serialVersionUID = 1L;
	private static final int SERVER_PORT = 1099;
	
	Deposito myDeposito;
	protected ServerImpl() throws RemoteException {
		super();
		myDeposito = new Deposito();
	}
	
	public static void main(String[] args) throws RemoteException {
		ServerImpl serverImpl = new ServerImpl();
		Registry registry = LocateRegistry.createRegistry(SERVER_PORT);
		registry.rebind("SERVER", serverImpl);
	}

	public void writeMessage(String message) throws RemoteException {
		myDeposito.write(message);
	}

	public Informazione readInfo(long lastt) throws RemoteException {	
		return myDeposito.read(lastt);
	}
}
