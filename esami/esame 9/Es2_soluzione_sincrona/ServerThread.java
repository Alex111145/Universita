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
			ois = new ObjectInputStream(cliSocket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void run() {
		String str = null;
		System.out.println("Server thread running");
		try {
			// si legge (una tantum) l'id del cliente che si serve
			iDgiocatore=(int) ois.readObject();
			while(true) {
				tavolo.aspettaTurno(iDgiocatore); // manda invito a giocare
				oos.writeObject("tocca a te");
				str = (String)ois.readObject(); // riveve mossa o indicazione fine
				if(str.equals("FineServizio")) {
					break;
				}
				tavolo.mossa(iDgiocatore, str);

			}
		} catch (ClassNotFoundException | IOException e) {
		} 
		try {
			System.out.println("Server socket completes");
			cliSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
