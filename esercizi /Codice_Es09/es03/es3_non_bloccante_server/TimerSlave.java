import java.rmi.RemoteException;

public class TimerSlave extends Thread {
	int delay;
	WarnClient theClient;
	public TimerSlave(int x, WarnClient c) {
		delay=x;
		theClient=c;
	}

	public void run() {
		try {
			sleep(delay*1000);
		} catch (InterruptedException e) { }
		try {
			theClient.notifyMe();
		} catch (RemoteException e) {}
	}
}
