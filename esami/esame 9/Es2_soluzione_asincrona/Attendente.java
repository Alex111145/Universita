import java.io.IOException;
import java.io.ObjectInputStream;

public class Attendente extends Thread {
	int titolare;
	ObjectInputStream ois;
	Giocatore giocatore; // thread genitore
	Attendente(int chi, ObjectInputStream in, Giocatore g){
		titolare=chi;
		ois=in;
		giocatore=g;
	}
	public void run() {
		String str;
		boolean finito=false;
		System.out.println("thread slave attendente di "+titolare+" running");
		try {
			while(!finito) {
				str = (String) ois.readObject();
				System.out.println("thread slave attendente di "+titolare+" received "+str);
				if(str.equals("tocca a te")) {
					giocatore.setFlag(true);
					finito=true;
				}
			}
		} catch (ClassNotFoundException | IOException e) {
			System.err.println("communication error. Terminating.");
			System.exit(0);
		}
	}
}
