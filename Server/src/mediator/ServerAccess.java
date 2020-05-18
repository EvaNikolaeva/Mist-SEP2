package mediator;

import java.rmi.RemoteException;

public interface ServerAccess
{
  ServerRead acquireRead() throws RemoteException;
  ServerWrite acquireWrite() throws RemoteException;
  void releaseRead() throws RemoteException;
  void releaseWrite() throws RemoteException;
}
