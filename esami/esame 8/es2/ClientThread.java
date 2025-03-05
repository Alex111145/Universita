import java.io.*;
import java.net.*;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ClientThread extends Thread {
	ObjectOutputStream oos;
	ObjectInputStream ois;
	Socket mySocket;
	Info myInfo;
	int myId;
	String keywords[] = {"potipu", "patagarru", "svicolone", "topogigio", "simplicius"};
	ClientThread(int n){
		myId=n;
		myInfo=new Info();
		try {
			mySocket = new Socket(InetAddress.getByName(null), DatiServer.PORT);
			oos = new ObjectOutputStream(mySocket.getOutputStream());
			ois = new ObjectInputStream(mySocket.getInputStream());
		} catch (IOException e) { e.printStackTrace(); }
		this.setName("client_"+myId);
	}
	private void elaboro() {
		int a=0;
		String str;
		System.out.println("Client"+myId+": elaboro ... ");
		for(int i=0; i<1000000; i++) {
			if((str=myInfo.getInfoArrived())!=null) {
				System.out.println("Client"+myId+": arrivata notifica: "+str);
			}
			for(int j=0; j<1000000; j++) {
				a=1-a;
			}
		}
	}
	private void mySleep(int i1, int i2) {
		try {
			Thread.sleep(ThreadLocalRandom.current().nextInt(i1, i2));
		} catch (InterruptedException e) { }
	}
	public void run() {
		Random rnd=new Random();
		try {
			if((myId%2)==1) {
				while(true) {
					int i = rnd.nextInt(5);
					oos.writeObject("Aggiungi");
					oos.writeObject(keywords[i]);
					oos.writeObject("info "+keywords[i]);
					mySleep(0, 2000);
				}
			} else {
				new ListenerThread(myInfo, ois).start();
				while(true) {
					String chiaveDaCercare=keywords[rnd.nextInt(5)];
					mySleep(0, 2000);
					System.out.println("Client"+myId+": cerco "+chiaveDaCercare);
					oos.writeObject("Trova");
					oos.writeObject(chiaveDaCercare);
					elaboro();
				}
			}
			// oos.writeObject("FineServizio");
			// mySocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		for(int j=0; j<4; j++) {
			new ClientThread(j).start();
		}
	}
}
