package common;
/**  5810404928 Chotika Luangorachorn  */
import common.models.Event;
import common.models.Schedule;

import java.sql.Connection;
import java.sql.SQLException;

public interface DBService {
    Connection connectionDB() throws ClassNotFoundException, SQLException;
    void insertToDB(Event event);
    void selectToDB();
    void deleteToDB(Event event);
    void updateToDB(Event event);
    Schedule getSchedule();
}
