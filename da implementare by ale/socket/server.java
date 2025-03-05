//package socket; ( questo non metterlo)

import java.net.*;
import java.io.*;

public class Server {
	
	//"nome classe con wait A " "abbreviato B inventato"; // QUA DEVI TOGLIERE IL COMMENTO 
     Tavolo(A) tavolo(B); //( NON DA METTERE ) CLASSE Tavolo esiste
	Server(){
		B= new A ();	
	}
	public void exec() throws IOException {
		ServerSocket s = new ServerSocket(8999);
		System.out.println("Server inizia");
		try {
			while (true) {
				Socket socket = s.accept();
				System.out.println("Server accepted connection");
				new SlaveThread(socket, tavolo(B)).start(); // QUA DEVI TOGLIERE IL COMMENTO ATTENTO A "B" È IL NOME ABBREVIATO DELLA CLASSE CON WAIT
			}
		} finally {
			s.close();
		}
	}
	public static void main(String[] args) throws IOException {
		new Server().exec();
	}
}

