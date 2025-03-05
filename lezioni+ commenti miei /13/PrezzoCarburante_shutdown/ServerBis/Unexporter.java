import java.rmi.NoSuchObjectException;
import java.rmi.Remote;
import java.rmi.server.UnicastRemoteObject;

public class Unexporter extends Thread {
	Remote toBeUnexported;
	Unexporter(Remote obj){
		toBeUnexported=obj;
	}
	public void run() {
		try { Thread.sleep(2000); } catch (InterruptedException e) {}
		try {
			if(UnicastRemoteObject.unexportObject(toBeUnexported, false)) {
				System.out.println("Unexporting succeeded.");				
			} else {
				System.out.println("Unexporting failed.");
			}
		} catch (NoSuchObjectException e) {
			System.err.println("Unexporting failed by exception.");
		}
	}

}
