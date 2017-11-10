package common.models;

import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import static org.junit.Assert.*;

public class ScheduleTest {

    private Schedule schedule;
    private Event event;

    @Before
    public void setUp() throws Exception {
        schedule = new Schedule();
        event = new Event("10/11/17","11:02","Never", "project","calendar");
        schedule.addEvent(event);
    }

    @Test
    public void addEvent() throws Exception {
        Event event = new Event("10/11/17","11:55","Never", "project","calendar");
        schedule.addEvent(event);

        assertTrue(schedule.getEvents().indexOf(event)!=-1);
    }

    @Test
    public void getEventsSearch() throws Exception {
        DateFormat dateformat = new SimpleDateFormat("dd/MM/yy", Locale.ENGLISH);
        Date dateSearch = dateformat.parse("10/11/17");
        ArrayList<Event> events = schedule.getEventsSearch(dateSearch);

        assertEquals(events.get(0).getDate(),"10/11/17");
    }

    @Test
    public void clear() throws Exception {
        assertFalse(schedule.getEvents().size()==0);
        schedule.clear();
        assertTrue(schedule.getEvents().size()==0);

    }

    @Test
    public void deleteEvent() throws Exception {
        schedule.deleteEvent(event);
        assertTrue(schedule.getEvents().indexOf(event)==-1);
    }

    @Test
    public void editEvent() throws Exception {
        assertEquals(schedule.getEvents().get(0).getTopic(),"project");

        event.setTopic("Test");
        schedule.editEvent(event);

        assertEquals(schedule.getEvents().get(0).getTopic(),"Test");
    }

}