import java.io.*;
import java.net.Socket;
import java.util.concurrent.ThreadLocalRandom;

public class SlaveThread extends Thread {
	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	protected SlaveThread(Socket s) throws IOException  {
		socket = s;
		out = new ObjectOutputStream(s.getOutputStream());
		in = new ObjectInputStream(s.getInputStream());
	}

	public void run() {
		try {
			while (true) {
				try { Thread.sleep(1000); } catch (InterruptedException e) {}
				out.writeObject("vai");
			}
		} catch (IOException e) {
			System.err.println("IO Exception ");
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				System.err.println("Socket not closed");
			}
		}
	}
}
