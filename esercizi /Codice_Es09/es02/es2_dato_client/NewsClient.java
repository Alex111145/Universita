import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.NotBoundException;
import java.rmi.RemoteException; 

public class NewsClient {
	static final int N=6;
	public void exec(String hostName) {
		String st;
		Registry registro;
		try {
			registro = LocateRegistry.getRegistry(hostName,1099);
			NewsService news = (NewsService) registro.lookup("NEWS");
			for(int i=0; i<N; i++){
				st=news.readNews();
				System.out.println("client: "+st);
			}
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
		System.out.println("Finito");	
	}
	public static void main(String[] args) {
		if(args.length==0) {
			new NewsClient().exec(null);
		} else {
			new NewsClient().exec(args[0]);
		}
	}
}
