
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;


public class Reader extends Thread {
	int miaId;
	Registry reg;
	public Reader(int id) {
		miaId=id;
	}
	public void run() {
		String s;
		try {
			reg = LocateRegistry.getRegistry(1099);
			DataObjectInterface data=(DataObjectInterface) reg.lookup("DataObjectServer");
			System.out.println("Reader "+miaId+": inizio");
			while(true) {
				s=data.get();
				System.out.println("Reader "+miaId+" ha letto "+s);
			}
		} catch (RemoteException | NotBoundException e) {
		}
	}
	public static void main(String args[]) {
		Random rnd=new Random();
		new Reader(rnd.nextInt(1000)).start();
	}
}
