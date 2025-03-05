import java.util.concurrent.*;

public class RowSummer extends Thread {
	private int row;
	private int vect[];
	private int results[];
	private CyclicBarrier myBarrier;
	public RowSummer(int row, int m[], int r[], CyclicBarrier b) {
		this.row=row;
		this.vect=m;
		this.results=r;
		this.myBarrier=b;
	}
	public void run() {
		int columns=vect.length;
		int sum=0;
		for(int i=0; i<columns; i++) {
			sum += vect[i];
		}
		results[row]=sum;
		System.out.println("Result for row "+row+" is: " + sum);
		try {
			myBarrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {	}
	}
}
