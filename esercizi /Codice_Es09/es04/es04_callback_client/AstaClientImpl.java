import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.NotBoundException;
import java.rmi.RemoteException; 
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ThreadLocalRandom;

public class AstaClientImpl extends UnicastRemoteObject implements AstaClient{
	private static final long serialVersionUID = 1L;
	private int maxTries = 3+(int)(Math.random()*4);
	int numTries=0;
	private final double myIncrease=1.1;
	private String myName;
	private Offerta offertaCorrente=null;
	AstaClientImpl() throws RemoteException {
		super();
		this.myName="partecipante_"+(int)(Math.random()*400);
		this.numTries=0;
	}
	private void exec() throws RemoteException, NotBoundException{
		Registry registro = LocateRegistry.getRegistry(1099);
		AstaServer server = (AstaServer) registro.lookup("ASTA");
		server.addClient(this);
		offertaCorrente=server.letturaOffertaCorrente();
		System.out.println(myName+": ho letto "+offertaCorrente);
		while(true){
			try {
				Thread.sleep(ThreadLocalRandom.current().nextInt(100, 300));
			} catch (InterruptedException e) {}
			// System.out.println(myName+" in ciclo con "+offertaCorrente);
			TipoEsito esito=offertaCorrente.getEsito();
			int am=offertaCorrente.getAmount();
			String chi=offertaCorrente.getWho();
			if(esito.equals(TipoEsito.Aggiudicata)) {
				System.out.println(myName+" aggiudicata a "+chi);
				if(chi.equals(myName)) {
					System.out.println(myName+": ho vinto!");
				} else {
					System.out.println(myName+": ho perso!");
				}
				break;
			} else {
				System.out.println(myName+" esito= "+esito);
				if(numTries<maxTries) {
					if(!chi.equals(myName)) {
						numTries++;
						System.out.println(myName+" offering "+(int) (am*myIncrease));
						server.propostaOfferta(new Offerta((int) (am*myIncrease), myName, TipoEsito.Proposta));
					}
				} else { // ho finito i tentativi
					if(!chi.equals(myName)) {
						System.out.println(myName+" lascio! ");
						break;
					}
				}
			}
		}
		server.removeClient(this);
		UnicastRemoteObject.unexportObject(this, false);
	}
	public void notificaNuovaOfferta(Offerta o) throws RemoteException {
		offertaCorrente=o;
		System.out.println(myName+": ho ricevuto notifica di "+offertaCorrente);
	}
	public static void main(String[] args) throws RemoteException, NotBoundException {
		AstaClientImpl me = new AstaClientImpl();
		me.exec();
	}

}

