import java.io.*;
import java.util.StringTokenizer;

public class Translator {
	BufferedReader dictionary;
	File file;
	Translator(String s){
		file = new File(s);
		try {
			dictionary = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			System.err.println("Il file [" + file.getAbsolutePath() + "] non esiste!");
		}
	}
	String getTranslation(String inWord) throws FileNotFoundException {
		BufferedReader tmpDictionary;
		boolean trovato=false;
		String line, tmp;
		StringTokenizer stk=null;
		tmpDictionary = new BufferedReader(new FileReader(file));
		while(!trovato) {
			try {
				line=tmpDictionary.readLine();
				if(line==null) {
					return "translation not available";
				}
				stk = new StringTokenizer(line, " ");
				tmp = stk.nextToken();
			} catch (IOException e) {
				return "dictionary I/O problems";
			}
			if(tmp.equals(inWord)) {
				trovato=true;
			}
		}
		return stk.nextToken();
	}
}
