package mediator;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerAccess
{
  void acquireRead() throws RemoteException;
  void acquireWrite() throws RemoteException;
  void releaseRead() throws RemoteException;
  void releaseWrite() throws RemoteException;
}
