public class CorsaDiCavalli {
	final int NUM_CAVALLI=3;
	final int NUM_GIRI=3;
	private void exec() {
		Barrier barriera=new Barrier(NUM_CAVALLI);
		for(int i=0; i<NUM_CAVALLI; i++) {
			new Cavallo(i+1, barriera, NUM_GIRI).start();
		}
	}
	public static void main(String args[]) {
		new CorsaDiCavalli().exec();
	}
}
