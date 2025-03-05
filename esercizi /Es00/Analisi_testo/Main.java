import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.LinkedList;

public class Main {

  public static void main(String[] args) {
    // canale di ouptut standard
    PrintStream out = System.out;

    if (args.length != 1)
      out.println("Uso: cmd nome_file");
    else {
      File file = new File(args[0]);
      if (!file.exists())
        out.println("Il file [" + file.getAbsolutePath() + "] non esiste!");
      else {
        try {
          Testo testo = new Testo(file);
          out.println("         Numero parole: " + testo.numeroParole());
          out.println("Numero parole distinte: " + testo.numeroParoleDistinte());

          String[] daCercare = {"colle", "caro", "siepe", "mare", "e"};
          for(String s: daCercare)
            out.println("Ci sono " + testo.contaOccorrenzeParola(s) + " occorrenze della parola \"" + s  + "\"");

          out.println("Parole in ordine alfabetico:");
          LinkedList<String> paroleInOrdineAlf = testo.paroleDistinteInOrdineAlfabetico();
          for(String s: paroleInOrdineAlf)
            out.print(s + " ");
          out.println();

        } catch (IOException e) {
          out.println("ERRORE: " + e.getMessage());
        }
      }
    }
  }
}
