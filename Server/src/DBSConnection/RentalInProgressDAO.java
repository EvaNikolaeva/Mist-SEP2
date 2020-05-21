package DBSConnection;

import model.Rental;

public interface RentalInProgressDAO
{
    void acceptRental(Rental rental);
    void declineRental(Rental rental);
}
