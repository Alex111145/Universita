
public class Info {
	boolean notificationArrived;
	String infoArrived;
	Info(){
		notificationArrived=false;
		infoArrived="boh";
	}
	public synchronized boolean isNotificationArrived() {
		return notificationArrived;
	}
	public synchronized void setNotificationArrived(boolean notificationArrived) {
		this.notificationArrived = notificationArrived;
	}
	public synchronized String getInfoArrived() {
		if(notificationArrived) {
			this.notificationArrived = false;
			return infoArrived;
		} else {
			return null;
		}
	}
	public synchronized void setInfoArrived(String infoArrived) {
		this.infoArrived = infoArrived;
		this.notificationArrived = true;
	}
}
