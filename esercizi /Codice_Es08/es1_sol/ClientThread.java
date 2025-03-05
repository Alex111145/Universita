import java.net.*;
import java.io.*;
import java.util.concurrent.*;
import java.util.Random;

class ClientThread extends Thread {

	// private static int counter = 0;
	private static int threadcount = 0;
	private int myID;
	private Socket socket;
	private ObjectInputStream ins;
	private ObjectOutputStream outs;

	public static int threadCount() {
		return threadcount;
	}
	public ClientThread(InetAddress addr, int id) {
		System.out.println("Making client " + id);
		myID=id;
		synchronized(ClientThread.class) {
			threadcount++;
		}
		try {
			socket = new Socket(addr, Server.PORT);
			outs = new ObjectOutputStream(socket.getOutputStream());
			ins = new ObjectInputStream(socket.getInputStream());
			this.start();
		} catch (IOException e) {
			System.err.println("Client "+myID+": proxy creation failed");
			synchronized(ClientThread.class) {
				threadcount--;
			}
		}
	}
	public void run() {
		Result res;
		OperationRequest req;
		Random rand = new Random();
		System.out.println("Client_"+myID+" running");
		int howMuch=0;
		int times=(rand.nextInt(10))+2;
		try {
			for (int i = 0; i < times; i++) {
				howMuch=new Random().nextInt(10,1000);
				if(new Random().nextBoolean()) {
					req=new OperationRequest(myID, howMuch, "Deposit");
				} else {
					req=new OperationRequest(myID, howMuch, "Withdraw");
				}
				try {
					outs.writeObject(req);
					res=(Result)ins.readObject();
					System.out.println(req);
					System.out.println(res);
				} catch (IOException | ClassNotFoundException e) {
					System.err.println("Proxy: operation not executed");
				}
				try {
					Thread.sleep(ThreadLocalRandom.current().nextInt(1, 4));
				} catch (InterruptedException e) {}
			}
			try {
				req=new OperationRequest(myID, 2, "Double");
				outs.writeObject(req);
				res=(Result)ins.readObject();
				System.out.println(req);
				System.out.println(res);
				outs.writeObject(new OperationRequest(-1, 0, "END"));
			} catch (IOException | ClassNotFoundException e) {
				System.err.println("Proxy: operation not executed");
			}
		}
		finally {
			synchronized(ClientThread.class) {
				threadcount--;
			}
		}
	}
}
