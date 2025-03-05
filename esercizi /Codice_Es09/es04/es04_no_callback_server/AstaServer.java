
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AstaServer extends Remote{
  public void propostaOfferta(Offerta o) throws RemoteException;
  public Offerta letturaOffertaCorrente() throws RemoteException;
}
