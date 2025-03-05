import java.io.*;

class TestIOTerminale {

    public static void main(String[] args) throws IOException {
        // costruiamo un buffered reader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //stream per la scrittura
        PrintStream out = System.out;
        out.print("Scrivi un numero > ");
        
        boolean completato=false;
        int n=0;
        while(!completato) {
            //lettura
            String s = br.readLine();
            try {
                n = Integer.parseInt(s);
            	completato=true;
            } catch(NumberFormatException e) {
                out.println("Input non riconosciuto");
                out.print("Scrivi un numero > ");            	
            }
            //stampa
            if(completato) {
                out.println("Numero scritto > " + n);
            }
        }
    }
}
