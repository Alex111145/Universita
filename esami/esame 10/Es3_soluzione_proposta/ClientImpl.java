import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

public class ClientImpl extends UnicastRemoteObject implements ClientInterface{
	private static final long serialVersionUID = 1L;
	String mioNome;
	int child_num;
    ChildThread childThread;	

	ClientImpl() throws RemoteException {
		this.mioNome="Il_client";
		child_num=0;
	    childThread=new ChildThread();	
	}
	private void exec() {
		Registry registry;        
		try {
			registry = LocateRegistry.getRegistry(1099);
			ServerInterface server = (ServerInterface) registry.lookup("SERVER");
			server.subscribe(this);
			System.out.println(mioNome+ ": subscribed");
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}
	public void vai() {
		System.out.println(mioNome+ ": command to start child received");
		new Thread(childThread, "child_"+(child_num++)).start();
	}

	public static void main(String[] args) throws NotBoundException, RemoteException{
		new ClientImpl().exec();
	}
}
