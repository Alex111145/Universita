import java.rmi.Remote;
import java.rmi.RemoteException;

public interface WarnServer extends Remote {
  public void warnAt(int X, WarnClient c) throws RemoteException;
}
