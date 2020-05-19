package model;

import java.io.Serializable;

public class Rental implements Serializable
{
  private User owner;
  private User requester;
  private Game game;
  private boolean isComplete;
  private int id; //this id will be handled by the database

  public Rental(User owner, User requester, Game game)
  {
    this.owner = owner;
    this.requester = requester;
    this.game = game;
    this.isComplete = false;
  }

  public User getOwner()
  {
    return owner;
  }

  public User getRequester()
  {
    return requester;
  }

  public Game getGame()
  {
    return game;
  }

  public boolean isComplete()
  {
    return isComplete;
  }

  public void setIsComplete(boolean isComplete)
  {
    this.isComplete = isComplete;
  }

  public int getId()
  {
    return id;
  }
}
