import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Client {
	String name ;
	Random rndRandom;

	Client() {
		rndRandom = new Random();
		this.name="Client_" + rndRandom.nextInt(100);
	}
	public void exec() throws IOException,NotBoundException{

		Registry registry = LocateRegistry.getRegistry(1099);
		DepositoInterface depositoInterface = (DepositoInterface) registry.lookup("SERVER");

		long lastTime=System.currentTimeMillis()-1000000;
		for(int j=0; j<10; j++) {
			try {
				Thread.sleep(ThreadLocalRandom.current().nextInt(300));
			} catch (InterruptedException e) { }
			Informazione lettura;
			lettura = depositoInterface.readInfo(lastTime);
			System.out.println(name+" leggo "+lettura);
			lastTime=lettura.getInfoTime();
		}
	}

	public static void main(String[] args) throws NotBoundException{
		try {
			new Client().exec();
		} catch (NotBoundException | IOException e) {
			System.err.println("Client KO");
		}
	}
}
