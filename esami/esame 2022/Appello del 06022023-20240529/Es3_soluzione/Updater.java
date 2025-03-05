import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Updater {
	String name ;
	Random rndRandom;

	Updater() {
		rndRandom = new Random();
		this.name="Updater_" + rndRandom.nextInt(100);
	}
	public void exec() throws IOException, NotBoundException{
		Registry registry = LocateRegistry.getRegistry(1099);
		DepositoInterface depositoInterface = (DepositoInterface) registry.lookup("SERVER");

		for(int j=0; j<10; j++) {
			try {
				Thread.sleep(ThreadLocalRandom.current().nextInt(500));
			} catch (InterruptedException e) { }

			String messageString = "Messaggio" + rndRandom.nextInt(1000);
			depositoInterface.writeMessage(messageString);
			System.out.println(name+" scrivo "+messageString);
		}

	}

	public static void main(String[] args) {
		try {
			new Updater().exec();
		} catch (IOException | NotBoundException e) {
			System.out.println("Updater KO");
		}
	}
}
