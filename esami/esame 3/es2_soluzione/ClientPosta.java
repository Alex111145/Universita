import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.ThreadLocalRandom;

class ClientPosta  {
	Socket socket;
	ObjectOutputStream out;
	ObjectInputStream in;
	String name;

	public void exec() {
		Messaggio msg;
		String dest = null;
		long timeout=150;
		int numClients;
		try {
			InetAddress addr = InetAddress.getByName(null);
			Socket socket=new Socket(addr, 8999);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			System.out.println("client started");
			try {
				Thread.sleep(ThreadLocalRandom.current().nextInt(200,2000));
			} catch (InterruptedException e) { }
			out.writeObject("newClient");
			name=(String) in.readObject();
			System.out.println(name+" received the name");
			for(int j=0; j<10; j++) {
				try {
					Thread.sleep(ThreadLocalRandom.current().nextInt(200,300));
				} catch (InterruptedException e) { }
				//				System.out.println(name+" iterazione "+j);	
				if(ThreadLocalRandom.current().nextBoolean()) {
					// manda messaggio
					out.writeObject("numClients");
					numClients=(int) in.readObject();
					dest="Client_"+ThreadLocalRandom.current().nextInt(1,1+numClients);
					msg=new Messaggio(name, dest, "msg da "+name+" #"+j);
					System.out.println(name+" scrivo "+msg);	
					out.writeObject("put");
					out.writeObject(msg);
					System.out.println(name+" scrittura completata");	
				} else {
					// legge messaggio
					out.writeObject("read");
					out.writeObject(name);
					out.writeObject(timeout);
					msg=(Messaggio) in.readObject();
					if(msg==null) {
						System.out.println(name+" letto niente :-(");
					} else {
						System.out.println(name+" ho letto "+msg);					
					}
				}
			}
			out.writeObject("end");
			out.close();
			in.close();
			socket.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[])  {
		new ClientPosta().exec();
		System.out.println("Client: ohibo`, termina main");
	}
}
