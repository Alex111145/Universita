import java.util.Scanner;

public class es_7 extends Thread{

	public void run(){
		for(;;){
			System.out.println("ciao");
			// NB: se l'interrupt arriva prima che il thread vada a dormire lo perdiamo!
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				System.out.println("thread interrotto: termino");
				break;
			}
		}
	}
	public static void main(String[] args) throws InterruptedException {
		es_7 t=new es_7();
		t.start();
		String cmd;
		Scanner sc = new Scanner(System.in);
		for(;;){
			System.out.println("Inserisci comando >");
			cmd=sc.next();
			if(cmd.equals("fine")){
				t.interrupt();
				System.out.println("Main ha letto il comando di terminazione ");
				break;
			} else {
				System.out.println("Main ha letto il comando "+cmd+" e lo ha ignorato");
			}
		}
	}
}

