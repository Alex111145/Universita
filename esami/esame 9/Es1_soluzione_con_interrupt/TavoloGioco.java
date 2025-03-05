public class TavoloGioco {
	TavoloGioco(){
	}
	public synchronized void mossa(int chi, String m) {
		System.out.println(chi+" ha fatto mossa "+m);
	}
}
