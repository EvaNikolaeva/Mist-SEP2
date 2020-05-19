package mediator;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerAccess extends Remote
{
  ServerRead acquireRead() throws RemoteException;
  ServerWrite acquireWrite() throws RemoteException;
  void releaseRead() throws RemoteException;
  void releaseWrite() throws RemoteException;
}
