import java.util.Random;

public class TavoloGioco {
	Random rnd;
	TavoloGioco(){
		rnd=new Random();
	}
	public synchronized boolean mossa(int chi, String m) {
		System.out.println("******* "+Thread.currentThread().getName()+" ha fatto mossa "+m);
		return rnd.nextBoolean();
	}
}
