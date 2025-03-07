import java.io.*;
import java.net.*;

public class ClienteAstaThread extends Thread{
	private int maxTries = 3+(int)(Math.random()*4);
	private int numTries=0;
	private int currentOffer=0;
	private int myOffer;
	private String currentWinner;
	private final double myIncrease=1.06;
	private String myName;
	private Socket mySocket;
	private BufferedReader in;
	private PrintWriter out;
	public ClienteAstaThread(Socket s, String n) throws IOException{
		this.mySocket=s;
		this.myName=n;
		this.in = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
		this.out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(mySocket.getOutputStream())), true);
		start();
	}
	public void run(){
		String str;
		boolean finito=false;
		while(!finito && numTries<maxTries){			
			try {
				out.println("read");
				str = in.readLine();
				currentOffer=Integer.parseInt(str);
				currentWinner=in.readLine();
				System.out.println(myName+" read offer of "+str+" from "+currentWinner);				
				if(!currentWinner.equals(myName)){	// rilancia
					myOffer=(int) (currentOffer*myIncrease);
					str=String.valueOf(myOffer);
					out.println("offer "+str+" "+myName);
					System.out.println(myName+" offering "+str);				
					str = in.readLine();
					// System.out.println(myName+" received reply "+str);				
					if(str.equals("KO")){
						System.out.println(myName+" offer declined :-(");				
						Thread.sleep(100);						
					} else {
						System.out.println(myName+" offer accepted :-)");				
					}
				} else {
					Thread.sleep(1500);
				}
			} catch (IOException | InterruptedException e1) { break; }
			numTries++;
		}  // fine ciclo
		out.println("END");
	}
}
