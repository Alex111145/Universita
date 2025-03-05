
public class Pool {
	enum State {Idle, OnWait, Active};
	State[] philStates;  //stato di ciascun filosofo
	int numSticks;       // numero di bastoncini
	int numPhilosophers; // numero di filosofi
	long lastPick[];     // quando ciascun filosofo ha preso l'ultimo bastoncino
	Pool(int np, int ns) {
		numPhilosophers=np;
		philStates=new State[np];
		for(int i=0; i<np; i++) {
			philStates[i]=State.Idle; // inizializza lo stato dei plosofi
		}
		lastPick = new long[np];
		long t=System.currentTimeMillis();
		for(int j=0; j<np; j++) {
			lastPick[j]=t;
		}
		numSticks=ns;
	}
	private void printout() {
		System.out.print("[");
		for(int i=0; i<numPhilosophers; i++) {
			System.out.print(i);
			switch(philStates[i]) {
			case Idle:
				System.out.print("-I ");
				break;
			case OnWait:
				System.out.print("-W("+lastPick[i]+") ");
				break;
			case Active:
				System.out.print("-A ");
				break;
			}
			
		}
		System.out.println("]");
	}
	private boolean somePhilWaiting() {
		boolean anybodyWaiting=false;
		for(int i=0; i<numPhilosophers; i++) {
			if(philStates[i]==State.OnWait) {
				anybodyWaiting=true;
				break;
			}
		}
		return anybodyWaiting;
	}
	private int pickWaitingPhil() {
		// si sceglie il philosofo che e` a digiuno da piu` tempo
		int p=-1;
		long oldestPick=-1;
		printout();
		for(int i=0; i<numPhilosophers; i++) {
			if(philStates[i]==State.OnWait) {
				if(p==-1) {
					p=i;
					oldestPick=lastPick[i];
				} else {
					if(lastPick[i]<oldestPick) {
						oldestPick=lastPick[i];
						p=i;
					}
				}
			}
		}
		return p;
	}
	public synchronized void takeTwo(int philId) {
		System.out.println("Pool: taketwo called by Phil "+philId);
		philStates[philId]=State.OnWait;
		notifyAll();  // sveglia il waiter
		while(philStates[philId]==State.OnWait) {
			try { wait(); } catch (InterruptedException e) {}
		}
		lastPick[philId]=System.currentTimeMillis();
		System.out.println("Pool: two taken by Phil "+philId);
	}
	public synchronized void leaveTwo(int philId) {
		System.out.println("Pool: two left by Phil "+philId);
		numSticks+=2;
		philStates[philId]=State.Idle;
		notifyAll();
	}
	public synchronized void scheduleNext() {
		System.out.println("Pool: waiter tries to schedule some Phil");
		while(numSticks<2 || !somePhilWaiting()) {
			try { wait(); } catch (InterruptedException e) {}
		}
		// pick a waiting Philosopher and wake it up
		int idP=pickWaitingPhil();
		if(idP==-1) {
			System.out.println("Waiter: niente da fare");
			try { Thread.sleep(100); } catch (InterruptedException e) {}
		} else {
			System.out.println("Pool: waiter wakes up Phil "+idP);
			philStates[idP]=State.Active;
			numSticks-=2;
			notifyAll();
		}
	}
}

