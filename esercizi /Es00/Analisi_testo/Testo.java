import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Testo {

  private File file = null; // il file da cui viene letto il testo
  private LinkedList<String> paroleDelTesto = null; /* struttura dati contenente le
                                                       parole del testo nell'ordine in cui compaionio */

  private int numeroParoleDistinte = 0;  // il numero delle parole distinte trovate nel testo

  /**
   * Costruisce l'oggetto in cui il testo è letto dal file di testo
   * specificato come argomento. Il metodo non controlla che l'argomento
   * sia il riferimento ad un file esistente e che si tratti di un file di testo.
   *
   * @param file il file di testo da cui leggete il testo.
   */
  public Testo(File file) throws IOException {
    this.file = file;

    // costruiamo il buffered-reader per leggere il file
    BufferedReader reader = new BufferedReader(new FileReader(this.file));

    // costruiamo la struttura dati per memorizzare le parole
    this.paroleDelTesto = new LinkedList<String>();

    // leggiamo e memorizziamo le parole
    String riga;
    while ((riga = reader.readLine()) != null) {
      // spezziamo la riga in parole (seq. di char separati da spazi o punteggiatura)
      StringTokenizer stk = new StringTokenizer(riga, " ,.;:'\"");
      // estraiamo le parole dalla riga
      while (stk.hasMoreTokens()) {
        String nuovaParola = stk.nextToken().toLowerCase(); // prendiamo la prossima parola in minuscolo
        if (!paroleDelTesto.contains(nuovaParola))
          numeroParoleDistinte++;
        paroleDelTesto.add(nuovaParola);
      }
    }
  }

  /**
   * Restituisce il numero di paroleDelTesto che occorrono nel testo modellato da
   * questo oggetto.
   *
   * @return il numero di paroleDelTesto nel testo.
   */
  public int numeroParole() {
    return paroleDelTesto.size();
  }

  /**
   * Restituisce il numero di paroleDelTesto distinte che occorrono nel testo modellato da
   * questo oggetto.
   *
   * @return il numero di paroleDelTesto distinte nel testo.
   */
  public int numeroParoleDistinte() {
    return numeroParoleDistinte;
  }

  /**
   * Restituisce il numero di occorrenze della parola cercata in questo testo.
   *
   * @param daCercare la parola di cui contare le occorrenze.
   * @return il numero delle occorenze della parola.
   */
  public int contaOccorrenzeParola(String daCercare) {
    // scorriamo la lista e cerchiamo le occorrenze della parola da cercare
    int contatore = 0;
    for (String parola : paroleDelTesto)
      if (parola.equals(daCercare))
        contatore++;

    return contatore;
  }

  /**
   * Restituisce la lista delle parole del testo (senza ripetizioni) in ordine alfabetico.
   *
   * @return la lista delle parole del testo (senza ripetizioni) in ordine alfabetico.
   */
  public LinkedList<String> paroleDistinteInOrdineAlfabetico() {
    LinkedList<String> risultato = new LinkedList<String>();
    for (String s : paroleDelTesto)
      inserisciNelPostoGiusto(s, risultato);

    return risultato;
  }

  /*
   * Data una lista ordinata (ordine alfabtico crescente), inserisce la parola da inserire al posto giusto.
   */
  private void inserisciNelPostoGiusto(String parolaDaInserire, LinkedList<String> listaOrdinata) {
    // se la parola è già presente non la inseriamo
    if (!listaOrdinata.contains(parolaDaInserire)) {
      int indiceInserimento = 0; // la posizione in cui andrà inserita la parola

      // incrementiamo l'indice fintanto che la parola nella corrispondente
      // posizione è minore di quella da inserire
      while (indiceInserimento < listaOrdinata.size() && (listaOrdinata.get(indiceInserimento).compareTo(parolaDaInserire) < 0))
        indiceInserimento++;

      // inseriamo la parola
      listaOrdinata.add(indiceInserimento, parolaDaInserire);
    }
  }
}
