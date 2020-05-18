package model;

import java.time.LocalDate;

public interface AddGameModel
{
  void addGame(Game game);
  void validateGame(String name, String type, String releaseYear,
      LocalDate rentalFrom, LocalDate rentalTo, String availablePeriod,
      boolean needsDeposit);
}
