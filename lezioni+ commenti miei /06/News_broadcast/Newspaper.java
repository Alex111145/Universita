import java.util.concurrent.*;

class Newspaper extends Thread {
	String name;
	Broadcast<String> broadcast;
	Newspaper(Broadcast<String> b, String n) {
		this.broadcast=b;
		this.name=n;
	}
	public void run() {
		for(int i=0; i<4; i++){
			System.out.println(this.name + ": attendo news ...");
			try {
				String msg=broadcast.receive();
				System.out.println(this.name+": "+msg);
				// Thread.sleep(ThreadLocalRandom.current().nextInt(30));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
		}
	}
}

