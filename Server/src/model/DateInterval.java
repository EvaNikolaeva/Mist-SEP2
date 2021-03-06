package model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateInterval implements Serializable
{
    private Calendar startDate;
    private Calendar endDate;
    public DateInterval(Calendar fromDate, Calendar toDate){
        this.startDate = fromDate;
        this.endDate = toDate;
    }
    public DateInterval(int startDay, int startMonth, int endDay, int endMonth)
    {
        this.startDate = new GregorianCalendar(0, startMonth - 1, startDay);
        this.endDate = new GregorianCalendar(0, endMonth - 1, endDay);
    }

    private static String getCalendarDate(Calendar calendarDate)
    {
        return (calendarDate.get(Calendar.DAY_OF_MONTH)) + "/" + (calendarDate.get(Calendar.MONTH) + 1);
    }

    public String getStartDate()
    {
        return getCalendarDate(startDate);
    }

    public String getEndDate()
    {
        return getCalendarDate(endDate);
    }

    public String toString()
    {
        return getCalendarDate(startDate) + " -" + getCalendarDate(endDate);
    }
}