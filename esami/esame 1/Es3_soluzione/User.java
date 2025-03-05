import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.*;

public class User {
	ResManagerInterface repository;
	Resource rA=null, rB=null;
	String mioNome;
	void acquisizioneA() throws RemoteException {
		System.out.println(mioNome+" acquisisco risorsa A ");
		rA=repository.getA();
		System.out.println(mioNome+" acquisito risorsa "+rA.getType()+rA.getNum());
	}
	void acquisizioneB() throws RemoteException{
		System.out.println(mioNome+" acquisisco risorsa B ");
		rB=repository.getB();
		System.out.println(mioNome+" acquisito risorsa "+rB.getType()+rB.getNum());
	}
	void rilascio(Resource r) throws RemoteException {
		System.out.println(mioNome+" rilascio risorsa "+r.getType()+r.getNum());
		repository.put(r);
	}

	public void exec() throws RemoteException, NotBoundException {
		Registry reg = LocateRegistry.getRegistry();
		repository=	(ResManagerInterface) reg.lookup("GestoreRisorse");
		mioNome="utilizzatore_"+ThreadLocalRandom.current().nextInt(0, 10000);
		try {
			for(int i=0; i<100; i++){
				rA=rB=null;
				if(ThreadLocalRandom.current().nextBoolean()) {
					// ho bisogno di una risorsa
					if(ThreadLocalRandom.current().nextBoolean()) {
						acquisizioneA();
					} else {
						acquisizioneB();
					}
					// vediamo se c'e` bisogno di una seconda risorsa
					if(ThreadLocalRandom.current().nextBoolean()) {
						// ho bisogno della seconda risorsa
						// che deve essere di tipo diverso dalla prima
						if(rA!=null) {
							acquisizioneB();
						} else {
							rilascio(rB);
							acquisizioneA();
							acquisizioneB();
						}
					}
				}
				// elaborazione
				System.out.println(mioNome+" inizio elab. ");
				Thread.sleep(500);
				System.out.println(mioNome+" fine elab. ");
				//rilascio eventuali risorse
				if(rA!=null) {
					rilascio(rA);
				}
				if(rB!=null) {
					rilascio(rB);
				}
				Thread.sleep(200);
			}
			//			System.out.println(mioNome+" finished");
		} catch (InterruptedException e) { }
	}
	public static void main(String[] args) throws RemoteException, NotBoundException {
		new User().exec();
	}
}
