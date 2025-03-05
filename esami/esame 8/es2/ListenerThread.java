import java.io.IOException;
import java.io.ObjectInputStream;

public class ListenerThread extends Thread {
	
	Info theInfo;
	ObjectInputStream ois;
	ListenerThread(Info i, ObjectInputStream is){
		theInfo=i;
		ois=is;
	}
	public void run() {
		String str;
		while(true) {
			try {
				str= (String) ois.readObject();
				theInfo.setInfoArrived(str);
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
				break;
			}
		}
	}
}
