import java.net.*;
import java.io.*;
import java.util.concurrent.*;
import java.util.Random;

class ClientThread extends Thread {

	// private static int counter = 0;
	private static int threadcount = 0;
	private int myID;
	BankProxy myBank;
	public Result res;

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
			myBank=new BankProxy(addr);
			this.start();
		} catch (IOException e) {
			System.err.println("Client "+myID+": proxy creation failed");
			synchronized(ClientThread.class) {
				threadcount--;
			}
		}
	}
	// method simulating some activity
	private void doSometing() {
		boolean finished=false;
		while(!finished) {
			finished=res.isCompleted();
			// System.out.println(res);
			if(finished) {
				System.out.println("Client thread "+myID+" exiting doSomething *********");
			}
			System.out.println("client working");
			try {
				Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 1500));
			} catch (InterruptedException e) {}
		}
	}

	public void run() {
		OperationRequest req;
		Random rand = new Random();
		System.out.println("Client_"+myID+" running");
		int howMuch=0;
		int times=(rand.nextInt(10))+2;
		try {
			for (int i = 0; i < times; i++) {
				howMuch=new Random().nextInt(10,1000);
				if(new Random().nextBoolean()) {
					res=new Result(myID, howMuch, "Deposit", false);
					req=new OperationRequest(myID, howMuch, "Deposit");
				} else {
					res=new Result(myID, howMuch, "Withdraw", false);
					req=new OperationRequest(myID, howMuch, "Withdraw");
				}
				System.out.println(req);
				myBank.executeOperation(res, req);
				doSometing();
				System.out.println(res);
				try {
					Thread.sleep(ThreadLocalRandom.current().nextInt(1, 4));
				} catch (InterruptedException e) {}
			}
			req=new OperationRequest(myID, 2, "Double");
			myBank.executeOperation(res, req);
			System.out.println(req);
			
			
			
			System.out.println(res);
			myBank.quit();
		}
		finally {
			synchronized(ClientThread.class) {
				threadcount--;
			}
		}
	}
}
