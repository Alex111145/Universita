
public class ThreadExample extends Thread{
	public void run() {
		System.out.println("Ciao!");
	}
	public static void main(String args []) {
		ThreadExample t1=new ThreadExample(); // istanzio un thread
		//ThreadExample t2=new ThreadExample(); // istanzio un thread ne posso istanziare di piu
	    t1.start();
	}
}
