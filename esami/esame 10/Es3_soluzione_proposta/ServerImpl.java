import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ServerImpl extends UnicastRemoteObject implements ServerInterface{
	public static final long serialVersionUID = 1L;
	private static final int SERVER_PORT = 1099;
	List<ClientInterface>  clientList;
	String serverName;
	
	protected ServerImpl() throws RemoteException {
		super();
		clientList=new LinkedList<ClientInterface>();
		serverName=Thread.currentThread().getName();
	}
	
	private void notifyClients() {
		System.out.println(serverName+" notifies");
		if(clientList.size()==0) {
			System.out.println(serverName+": no client");
		} else {
			for(ClientInterface c:clientList) {
				try {
					System.out.println(serverName+": sending command");
					c.vai();
				} catch (RemoteException e) { }
			}
		}
	}
	private void exec() {
		while(true) {
			try {
				Thread.sleep(1000);
				Thread.sleep(ThreadLocalRandom.current().nextInt(1000));
			} catch (InterruptedException e) { }
			System.out.println(serverName+": time to send command");
			notifyClients();
		}
	}
	public static void main(String[] args) throws RemoteException {
		ServerImpl serverImpl;
		try {
			serverImpl = new ServerImpl();
			Registry registry = LocateRegistry.createRegistry(SERVER_PORT);
			registry.rebind("SERVER", serverImpl);
			System.out.println("server ready");
			serverImpl.exec();
		} catch (RemoteException e) {
			System.out.println("Server start failed");
			System.exit(0);
		}
	}

	public void subscribe(ClientInterface c) throws RemoteException {
		System.out.println(serverName+": subscription request received");
		clientList.add(c);
		if(clientList.size()<=0) {
			System.out.println(serverName+": client registration failed");
			System.exit(0);
		} else {
			System.out.println(serverName+": client registration completed");
		}
	}
}
