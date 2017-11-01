package common;

import common.models.Event;
import common.models.Schedule;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by PC301 on 1/11/2560.
 */
public interface DBService {
    Connection connectionDB() throws ClassNotFoundException, SQLException;
    void insertToDB(Event event);
    void selectToDB();
    void deleteToDB(Event event);
    void updateToDB(Event event);
    Schedule getSchedule();
}
