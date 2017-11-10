package common;
/**  5810404928 Chotika Luangorachorn  */
import common.models.Event;

import java.util.ArrayList;
import java.util.Date;

public interface ScheduleServer {
    void addEvent(Event event);
    ArrayList<Event> getEvents();
    ArrayList<Event> getEventsSearch(Date dateSearch);
}
