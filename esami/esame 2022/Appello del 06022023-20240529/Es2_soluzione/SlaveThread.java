import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SlaveThread extends Thread {
	Socket cSocket;
	Deposito myDeposito;
	ObjectInputStream in;
	ObjectOutputStream out;

	public SlaveThread(Socket cSocket, Deposito dep) throws IOException {
		this.cSocket = cSocket;
		this.myDeposito = dep;
		out = new ObjectOutputStream(cSocket.getOutputStream());
		in = new ObjectInputStream(cSocket.getInputStream());
		start();
	}
	
	public void exec(String str) throws ClassNotFoundException, IOException{
		if(str.equals("write")) {
			String message = (String)in.readObject();
			myDeposito.write(message);
		}
		if(str.equals("read")) {
			long lasttime = (long)in.readObject();
			Informazione informazione = myDeposito.read(lasttime);
			out.writeObject(informazione);
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
				cSocket.close();
			} catch (IOException e) { }
		}
	}
}
