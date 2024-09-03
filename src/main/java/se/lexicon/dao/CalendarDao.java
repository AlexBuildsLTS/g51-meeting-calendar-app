package se.lexicon.dao;

import se.lexicon.model.Calendar;
import se.lexicon.model.User;

import java.util.List;

public interface CalendarDao {
    void addCalendar(Calendar calendar);
    List<Calendar> findCalendarsByUser(User user);
    Calendar findCalendarById(int id);
    void updateCalendar(Calendar calendar);
    void deleteCalendar(Calendar calendar);
}
