import java.io.*;

class CopiaFileArgs {
	FileReader frd;
	FileWriter fwr;

	public void exec(String fn_in, String fn_out) {
		//stream lettura da file
		try {
			frd = new FileReader(fn_in);
		} catch(FileNotFoundException e) {
			System.out.println("File "+fn_in+" non trovato");
			return;
		}
		//stream scrittura su file
		try {
			fwr = new FileWriter(fn_out);
		} catch(IOException e) {
			System.out.println("File "+fn_out+" non creabile");
			return;
		}

		//lettura e scrittura

		int i;
		try {
			while ((i = frd.read()) != -1) {
				fwr.write(i);
			}
		} catch (IOException e1) {
			System.out.println("Problema di I/O durante la copia");
		}

		//chiusura degli stream
		try {
			frd.close();
			fwr.flush();
			fwr.close();
		} catch(IOException e) {
			System.out.println("Problemi in chiusura");
		}
	}
	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Uso: java CopiaFileArgs <file_da_copiare> <file_copia>");
		} else {
			CopiaFileArgs cf=new CopiaFileArgs();
			cf.exec(args[0], args[1]);
		}
	}
}
