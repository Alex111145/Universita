import java.util.Random;

public class SlaveProvider extends Thread {
	DataObject data;
	Random rnd;

	public SlaveProvider(DataObject d)  {
		data=d;
		rnd=new Random();
	}

	public void run() {
		System.out.println("Provider thread started");
			boolean decision;
			while(true) {
				decision=rnd.nextBoolean();
				if(decision) {
					data.set("info_"+rnd.nextInt(100));
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {		}
			}
	}
}
