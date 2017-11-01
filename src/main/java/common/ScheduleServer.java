package common;

import common.models.Event;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by PC301 on 1/11/2560.
 */
public interface ScheduleServer {
    void addEvent(Event event);
    ArrayList<Event> getEvents();
    ArrayList<Event> getEventsSearch(Date dateSearch);
}
