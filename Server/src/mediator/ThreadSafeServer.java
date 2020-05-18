package mediator;

public class ThreadSafeServer implements ServerAccess
{
  private int writers;
  private int readers;
  private int waitingWriters;
  private GameListServer gameListServer;

  public ThreadSafeServer(GameListServer gameListServer)
  {
    this.waitingWriters = 0;
    this.writers = 0;
    this.readers = 0;
    this.gameListServer = gameListServer;
  }

  @Override public synchronized ServerRead acquireRead()
  {
    while (waitingWriters > 0 || writers > 0)
    {
      try
      {
        wait();
      }
      catch (Exception e)
      {
        e.printStackTrace();

      }
    }
    readers++;
    return gameListServer;
  }

  @Override public synchronized ServerWrite acquireWrite()
  {
    waitingWriters++;
    while (readers > 0 || writers > 0)
    {
      try
      {
        wait();
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
    waitingWriters--;
    writers++;
    return gameListServer;
  }

  @Override public synchronized void releaseRead()
  {
    readers--;
    if (readers == 0)
    {
      notify();
    }
  }

  @Override public synchronized void releaseWrite()
  {
    writers--;
    notifyAll();
  }
}
