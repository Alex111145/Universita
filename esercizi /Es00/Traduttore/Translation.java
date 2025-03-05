import java.io.*;

class Translation {
	// costruiamo un buffered reader
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	//stream per la scrittura
	PrintStream out = System.out;
	String inputWord;
	Translator theTranslator;

	Translation(String dictionaryPath){
		theTranslator=new Translator(dictionaryPath);
	}
	EsitoLettura leggiInput()  {
		String s;
		try {
			s= br.readLine();
		} catch(IOException e) {
			return EsitoLettura.ErroreIO;
		}
		if(s.equals("<Fine>")) {
			return EsitoLettura.LettoEOF;
		}
		inputWord = s;
		return EsitoLettura.LettoParola;
	}

	public void exec() throws FileNotFoundException {
		boolean completato=false;
		while(!completato) {
			out.print("Scrivi la parola da tradurre > ");
			EsitoLettura el=leggiInput();  // lettura
			switch(el) {
			case LettoParola:
				out.println(inputWord+" => " + theTranslator.getTranslation(inputWord));
				completato=false;
				break; 
			case LettoEOF:
				completato=true;
				break;
			case ErroreIO:
				out.println("I/O error: termino");
				completato=true;	   
			}
		}
	}

	public static void main(String[] args) throws IOException {
		if (args.length != 1)
			System.out.println("Uso: cmd <nome_file>");
		else {	        
			Translation tt=new Translation(args[0]);
			tt.exec();
			System.out.println("main termina");
		}
	}
}