import java.io.*;
public class CopiaBin {
  public static void main (String[] arg) throws IOException {
    int c = 0;
    FileInputStream in = new FileInputStream("/Users/alessio/Desktop/Uni/Triennale Ale/2 Anno/Prog concorrente (8cfu)/lezioni+ commenti miei /Codice_Lez00/copia_file_binari/pippo.txt"); //apertura di un file dove conosco il nome

    FileOutputStream out = new FileOutputStream("/Users/alessio/Desktop/Uni/Triennale Ale/2 Anno/Prog concorrente (8cfu)/lezioni+ commenti miei /Codice_Lez00/copia_file_binari/out.txt"); //creazione di un file dove conosco il nome per output 
    while ((c = in.read()) != -1) { //leggo un bytealla volta fino a quando trova qualcosa 
      out.write(c); // scrivo i byte nel file out
    }
    out.close();
    in.close();
  }
}
//copia qualunque cosa presente
