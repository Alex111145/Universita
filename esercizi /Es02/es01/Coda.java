import java.util.concurrent.Semaphore;

public class Coda {
	static int BUFFERSIZE;
	private int numItems = 0;
	private int[] valori;
	private int first, last; // last is the index of the
	// most recently inserted item
	Semaphore mutex, full, empty;
	Coda(int size){
		BUFFERSIZE=size;
		mutex = new Semaphore(1);
		full = new Semaphore(0);
		empty = new Semaphore(BUFFERSIZE);
		first=0; last=0;
		valori=new int[BUFFERSIZE];
	}
	void printWithName(String s, int v) {
		System.out.println(Thread.currentThread().getName()+s+v+"["+numItems+"]");		
	}
	public int getItem(){
		int tmp;
		try{
			full.acquire();
		} catch(InterruptedException e) {}
		try{
			mutex.acquire();
		} catch(InterruptedException e) {}
		numItems--;
		tmp=valori[first];
		first=(first+1)%BUFFERSIZE;
		printWithName(" letto ", tmp);
		mutex.release();
		empty.release();
		return tmp;
	}

	public void setItem(int v) {
		try{
			empty.acquire();
		} catch(InterruptedException e) {}
		try{
			mutex.acquire();
		} catch(InterruptedException e) {}
		valori[last]=v;
		last=(last+1)%BUFFERSIZE;
		numItems++;
		printWithName(" scritto ", v);
		mutex.release();
		full.release();
	}

	public int getCurrentSize(){
		return numItems;
	}
}

