import java.util.*;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PrezzoCarburanteImpl implements PrezzoCarburante {
	Registry registry;
	Hashtable<String, Distributore> distributori = new Hashtable<String, Distributore>();
	public Distributore getInfoDistributore(String name) throws RemoteException {
		return distributori.get(name);
	}
	public boolean setNewPrezzi(Distributore distr) throws RemoteException {
		Distributore d = distributori.get(distr.getName());
		if(d==null){
			System.err.println("setNewPrezzi: il distributore "+distr.getName()+ " non esiste");
			return false;
		}
		d.setPrezzoBenzina(distr.getPrezzoBenzina());
		d.setPrezzoDiesel(distr.getPrezzoDiesel());
		return true;
	}
	public List<String> getNomiDistributori() throws RemoteException {
		Enumeration<String> nomiDistr = distributori.keys();
		List<String> list = Collections.list(nomiDistr);
		return list;
	}
	public void addDistributore(Distributore distr) throws RemoteException {
		if (distributori.containsKey(distr.getName())) {
			setNewPrezzi(distr);
			System.out.println("aggiornati prezzi per distributore "+ distr.getName());
		} else {
			distributori.put(distr.getName(), distr);
			System.out.println("aggiunto nuovo distributore " +distr.getName());
		}
	}
	public void shutdown() throws RemoteException {
		System.out.println("Shutdown request received");
		new Unexporter(this).start();
		System.out.println("Shutdown request completed");
	}
	private void exec() throws RemoteException {
		PrezzoCarburanteImpl obj = new PrezzoCarburanteImpl();
		PrezzoCarburante stub = (PrezzoCarburante) UnicastRemoteObject.exportObject(obj, 3939);
		registry = LocateRegistry.createRegistry(1099);
		registry.rebind("PrezziCarburante", stub);
		System.out.println("Server ready");
	}
	public static void main(String args[]) {
		try {
			new PrezzoCarburanteImpl().exec();
		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
	}
}

