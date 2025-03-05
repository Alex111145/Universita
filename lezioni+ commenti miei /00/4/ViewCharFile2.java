import java.io.*;
public class ViewCharFile2 {
  public static void main(String[] args) throws IOException {
    FileReader frd = new FileReader(args[0]);
    BufferedReader bfr = new BufferedReader(frd); // come argomento un filereader come se buttassi tutto il file dentro al buffer
    String str;
    while ((str = bfr.readLine()) != null) // legge una riga alla volta che finisce con \n,\r
      System.out.println(str);
    bfr.close();
    frd.close();
  }
}

// stampa sul terminale 