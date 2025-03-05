import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class NewsServiceImpl extends UnicastRemoteObject implements NewsService {
	private static final long serialVersionUID = 1L;
	String laNotizia;
	public NewsServiceImpl() throws RemoteException {
		super();
		laNotizia="...";
	}
	public String readNews() throws RemoteException {
		System.out.println("Server: lettura "+laNotizia);
		return laNotizia;
	}
	public void updateNews(String text) throws RemoteException {
		laNotizia=text;
		System.out.println("Server: notizia corrente="+laNotizia);
	}
}

