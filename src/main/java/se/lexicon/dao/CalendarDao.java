package se.lexicon.dao;

import se.lexicon.model.Calendar;
import se.lexicon.model.User;

public interface CalendarDao {
    void addCalendar(Calendar calendar);
    Calendar findCalendarByUser(User user);
    void updateCalendar(Calendar calendar);
    void deleteCalendar(Calendar calendar);
}
