import java.io.*;
import java.net.Socket;

public class SlaveThread extends Thread {
	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	Gioco ilGioco;

	public SlaveThread(Socket s, Gioco g)  throws IOException {
		socket = s;
		ilGioco=g;
		out = new ObjectOutputStream(s.getOutputStream());
		in = new ObjectInputStream(s.getInputStream());
	}
	public void exec(String str) throws ClassNotFoundException, IOException{
		if(str.equals("aspettaTurno")) {
			int id = (int)in.readObject();
			ilGioco.aspettaTurno(id);
			out.writeObject("done");
		}
		if(str.equals("attesaMossa")) {
			ilGioco.attesaMossa();
			out.writeObject("done");
		}
		if(str.equals("mossa")) {
			int id = (int)in.readObject();
			Mossa m=(Mossa)in.readObject();
			ilGioco.mossa(id, m);
			out.writeObject("done");
		}
		if(str.equals("letturaEsito")) {
			boolean b=ilGioco.letturaEsito();
			out.writeObject(b);
		}
		if(str.equals("letturaMossaCorrente")) {
			Mossa m=ilGioco.letturaMossaCorrente();
			out.writeObject(m);
		}
		if(str.equals("approva")) {
			boolean b = (boolean)in.readObject();
			ilGioco.approva(b);
			out.writeObject("done");
		}
	}
	public void run() {
		String command = "";
		boolean finito = false;
		System.out.println("slave started");
		try {
			while(!finito) {
				command=(String) in.readObject();
				System.out.println("command " + command + " received");
				if(command.equals("END")) {
					finito = true;
				}
				else {
					try {
						exec(command);
					} catch (ClassNotFoundException | IOException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (ClassNotFoundException | IOException e1) {
			e1.printStackTrace();
		}finally {
			try {
				socket.close();
			} catch (IOException e) { }
		}
	}
}
