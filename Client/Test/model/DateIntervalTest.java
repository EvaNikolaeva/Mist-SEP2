package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DateIntervalTest
{
  private LocalDate date1;
  private LocalDate date2;
  private DateInterval dateInterval;

  @BeforeEach void setUp()
  {
    date1 = LocalDate.of(2020, 4, 12);
    date2 = LocalDate.of(2020, 4, 20);
    dateInterval = new DateInterval(date1, date2);
  }

  @Test void DateInterval()
  {
    assertNotNull(date1);
    assertNotNull(date2);
    assertNotNull(dateInterval);
  }

  @Test void getStartDate()
  {
    assertEquals("12/4", dateInterval.getStartDate());
  }

  @Test void getEndDate()
  {
    assertEquals("20/4", dateInterval.getEndDate());
  }

  @Test void getStartDateObject()
  {

  }

  @Test void getEndDateObject()
  {

  }

  @Test void testToString()
  {
    assertEquals("12/4 - 20/4", dateInterval.toString());
  }
}
