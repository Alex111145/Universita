import java.io.*;

class TestIOTerminale {
	// costruiamo un buffered reader
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	//stream per la scrittura
	PrintStream out = System.out;
	int numeroLetto=0;

	EsitoLettura leggiInt()  {
		String s;
		try {
			s= br.readLine();
		} catch(IOException e) {
			return EsitoLettura.ErroreIO;
		}
		if(s.equals("Basta")) {
			return EsitoLettura.LettoEOF;
		}
		try {
			numeroLetto = Integer.parseInt(s);
			return EsitoLettura.LettoNumero;
		} catch(NumberFormatException e) {
			return EsitoLettura.LettoSchifezza;
		} 
	}

	public void exec() {
		boolean completato=false;
		while(!completato) {
			out.print("Scrivi un numero > ");
			EsitoLettura el=leggiInt();  // lettura
			switch(el) {
			   case LettoNumero:
					out.println("Numero scritto > " + numeroLetto);
					completato=false;
					break; 
			   case LettoEOF:
					completato=true;
					break;
			   case LettoSchifezza:
					out.println("Numero non riconosciuto");
					completato=false;
					break;
			   case ErroreIO:
					out.println("I/O error: termino");
					completato=true;	   
			}
		}
	}

	public static void main(String[] args) throws IOException {
		TestIOTerminale tt=new TestIOTerminale();
		tt.exec();
		System.out.println("main termina");
	}
}
