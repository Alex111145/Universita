import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerThread implements Runnable{
	Socket cliSocket;
	TavoloGioco tavolo;
	int iDgiocatore;
	ObjectOutputStream oos;
	ObjectInputStream ois;
	public ServerThread(Socket s, TavoloGioco t) {
		cliSocket = s;
		tavolo = t;
		try {
			oos = new ObjectOutputStream(cliSocket.getOutputStream());
			oos.flush();
			ois = new ObjectInputStream(cliSocket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void run() {
		String str = null;
		System.out.println("Server thread running");
		try {
			iDgiocatore=(int) ois.readObject();
			System.out.println("Server thread connected with giocatore "+iDgiocatore);
			while(true) {
				tavolo.aspettaTurno(iDgiocatore);
				System.out.println("Server thread connected with giocatore "+iDgiocatore+
						" invito a muovere");
				oos.writeObject("tocca a te");
				oos.flush();
				str = (String)ois.readObject();
				System.out.println("Server thread connected with giocatore "+iDgiocatore+
						" riceve "+str);
				if(str.equals("FineServizio")) {
					System.out.println("Server thread connected with giocatore "+iDgiocatore+
							" termina");
					tavolo.mossa(iDgiocatore, "chiude");
					break;
				} else {
					tavolo.mossa(iDgiocatore, str);
				}
			}
		} catch (ClassNotFoundException | IOException e) {
		} 
		try {
			System.out.println("Server thread connected with giocatore "+iDgiocatore+
					" chiude socket");
			cliSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
