import java.util.Scanner;

public class Interruptible_thread extends Thread{
	public void run() {
		int a=1;
		for(;;) {
			for(int i=0;i<10000; i++) {
				for(int j=0;j<10000; j++) {
					for(int z=0;z<100000; z++) {
						a=1-a;
					}
				}
			}
			if(this.isInterrupted()) {
				System.out.println("Thread termina per interrupt da sveglio");
				break;				
			}
			System.out.println("Ciao");
		}	
	}
	public static void main(String[] args) {
		Thread t=new Interruptible_thread();
		t.start();
		Scanner sc = new Scanner(System.in);
		for(;;) {
			System.out.println("Inserisci comando");
			String cmd=sc.next();
			if(cmd.equals("fine")) {
				t.interrupt();
				System.out.println("Fine: mandato interrupt");
				break;
			} else {
				System.out.println("comando ignorato");
			}
		}
		try {
			t.join();
		} catch (InterruptedException e) {}
		System.out.println("Main termina");
	}
}
