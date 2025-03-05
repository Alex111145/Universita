import java.io.*;

public class Dump {
  public static void main (String[] arg) throws IOException {
    FileInputStream in = new FileInputStream(arg[0]); 
    int n = Integer.parseInt(arg[1]); // max numero di caratteri da leggere
    int c = 0;
    int i = 0; // conta caratteri letti 
    String str = "    ";
    System.out.print(str);
    String car = "    ";
		
    while (((c = in.read()) != -1) && (i < n)) {
      str = Integer.toString(c);
      while (str.length() < 4) { str = " " + str; }
      System.out.print(str);
      i++;
      if (c < 31)
        car += '.'; // i car. di controllo diventano un punto
      else if (c < 128)
        car += (char)c; // caratteri ASCII
      else
        car += '*';     // caratteri > 127 diventano *
      if (i % 10 == 0) {    // ogni 10 caratteri un fineriga
        System.out.println(car);
        car = "    ";
        str = Integer.toString(i);
        while (str.length() < 4) { str = " " + str; }
        System.out.print(str);
      }
    }
    in.close();
  }
}


/*
 * 202 254 186 190 0 0 0 55 0 94             ****...7.^
10 10 0 18 0 32 7 0 33 10 0                  .... ..!..
20 2 0 34 10 0 35 0 36 8 0                  .."..#.$..
30 37 9 0 38 0 39 10 0 40 0                %..&.'..(.
40 41 10 0 2 0 42 10 0 35 0               )....*..#.
50 43 10 0 27 0 44 18 0 0 0                 +....,....
60 48 18 0 1 0 48 18 0 2                   0....0....
70 51 18 0 3 0 48 10 0 40 0                3....0..(.
80 53 10 0 2 0 54 7 0 55 7                5....6..7.
90 0 56 1 0 6 60 105 110 105 116         .8...<init
100
 * 
 * 
 */
