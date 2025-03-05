import java.io.*;
import java.util.*;

public class TheMain {
	String fileName;
	ObjectOutputStream output=null;
	ObjectInputStream ois = null;
	TheMain(String fn) throws FileNotFoundException, IOException{
		fileName=fn;
		output=new ObjectOutputStream(new FileOutputStream(fileName));
		ois = new ObjectInputStream(new FileInputStream(fileName));
	}
	void exec() {
		Point p=new Point(2,3);
		Point newP=null;
		try {
			output.writeObject(p);
			output.close();
		} catch (IOException e) {
			System.err.println("Error serializing");
			return;
		}
		System.out.println("Serializzazione completata.");
		try {
			newP = (Point) ois.readObject();
			ois.close();
		} catch (ClassNotFoundException | IOException e) {
			System.err.println("Error de-serializing");
		}
		System.out.println(newP);
	}

	public static void main(String[] args) {
		if(args.length==1) {
			TheMain tm = null;
			try {
				tm = new TheMain(args[0]);
			} catch (IOException e) {
				System.err.println("Failure");
				System.exit(0);
			}
			tm.exec();
		} else {
			System.out.println("filename missing");
		}
	}
}


