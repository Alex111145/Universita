import java.io.*;

public class ViewCharFile {
  public static void main(String[] args) throws IOException {
    FileReader frd = new FileReader("/Users/alessio/Desktop/Uni/Triennale Ale/2 Anno/Prog concorrente (8cfu)/lezioni+ commenti miei /Codice_Lez00/2/pippo.txt");
    // adesso uso il filereader prima ho usato lo string input, classe migliore per leggere caratteri in generale
    // questa classe implementa la possibilita di leggere da un path, se non cè il path va in errore
    
    int i=0;
    while ((i = frd.read()) != -1)
      System.out.print((char)i);
    frd.close();
  }
}
// legge e basta e stampa sul terminale
// potevo mettere un .read(char[] bf) legge solo i caratteri di lunghezza bf gli altri no 

