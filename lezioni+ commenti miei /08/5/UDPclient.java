import java.io.*;
import java.net.*;

public class UDPclient {
	BufferedReader inFromUser;
	DatagramSocket clientSocket;
	InetAddress IPAddress;
	byte[] sendData;
	byte[] receiveData;
	UDPclient() throws SocketException, UnknownHostException{
		inFromUser = new BufferedReader(new InputStreamReader(System.in));
		clientSocket = new DatagramSocket();
		IPAddress = InetAddress.getByName("localhost");
		sendData = new byte[1024];
		receiveData = new byte[1024];
	}
	private void exec() throws IOException {
		System.out.println("type a string: ");
		String sentence = inFromUser.readLine();
		sendData = sentence.getBytes();
		DatagramPacket sendPacket = new DatagramPacket(sendData,
				sendData.length, IPAddress, 9876);
		clientSocket.send(sendPacket);
		DatagramPacket receivePacket =
				new DatagramPacket(receiveData, receiveData.length);
		clientSocket.receive(receivePacket);
		String modifiedSentence=new String(receivePacket.getData());
		System.out.println("FROM SERVER:" + modifiedSentence);
		clientSocket.close();
	}
	public static void main(String args[]) {
		try {
			UDPclient cli = new UDPclient();
			cli.exec();
		} catch (IOException e) {
			System.out.println("Client failed");
		}
	}
}