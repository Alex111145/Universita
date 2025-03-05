
public class IrishPub {
	public final static int NUM_MUSICIANS=8;

	private void exec() throws InterruptedException{
		BeerAwaiters thirstyMusicians = new BeerAwaiters(NUM_MUSICIANS+1);
		ServedBeers servedBeers = new ServedBeers();
		Holder holder = new Holder(thirstyMusicians, servedBeers);
		holder.start();
		Musician musicians[] = new Musician[NUM_MUSICIANS];
		for(int i=0; i<NUM_MUSICIANS; i++){
			musicians[i] = new Musician("Musician_"+i, thirstyMusicians, servedBeers);
			musicians[i].start();
		}
		for(int i=0; i<NUM_MUSICIANS; i++){
			musicians[i].join();
		}
		System.out.println("The pub closes.");
	}
	public static void main(String[] args)   {
		try {
			new IrishPub().exec();
		} catch (InterruptedException e) {
			// N/A
		}
	}
}
