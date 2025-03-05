import java.io.*;
import java.net.Socket;

public class SlaveThread extends Thread {
	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	DataObject data;

	public SlaveThread(Socket s, DataObject d)  throws IOException {
		socket = s;
		data=d;
		out = new ObjectOutputStream(s.getOutputStream());
		in = new ObjectInputStream(s.getInputStream());
	}
	public void exec(String str) throws ClassNotFoundException, IOException{
		if(str.equals("get")) {
			String s=data.get();
			out.writeObject(s);
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
