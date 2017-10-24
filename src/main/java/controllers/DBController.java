/**  5810404928 Chotika Luangorachorn  */
package controllers;

import models.Event;
import models.Schedule;

import java.sql.*;

public class DBController {
    private Schedule schedule;
    private String url;
    public DBController(Schedule schedule){
        this.schedule = schedule;
    }

    /**connect database*/
    public Connection connectionDB() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        String dbURL = "jdbc:sqlite:" + url;
        Connection conn = DriverManager.getConnection(dbURL);
        return conn;
    }
    /**add event to Database*/
    public void insertToDB(Event event) {
        try{
            Connection conn = connectionDB();
            if (conn != null) {
                System.out.println("Connected to the database...");
                Statement statement = conn.createStatement();
                try{
                    String topic = (event.getTopic()).replace("'","\''");
                    String detail = (event.getDetail()).replace("'","\''");
                    String queryInsert = "insert into events "+"values('" + event.getDate()+"','"+event.getTime()+"','"+event.getType()+"','"+topic+"','"+detail+"')";
                    statement.executeUpdate(queryInsert);
                    System.out.println("up-------");
                    schedule.addEvent(event);
                }catch (SQLException ex){
                    System.out.println("duplicate data");}
                conn.close();
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**show event in Database*/
    public void selectToDB() {
        try{
            Connection conn = connectionDB();
            if (conn != null) {
                System.out.println("Connected to the database...");
                DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
                System.out.println("Driver name: " + dm.getDriverName());
                System.out.println("Product name: " + dm.getDatabaseProductName());
                Statement statement = conn.createStatement();
                String querySelect = "select * from events";
                ResultSet result =  statement.executeQuery(querySelect);
                while (result.next()){
                    String date = result.getString(1);
                    String time = result.getString(2);
                    String type = result.getString(3);
                    String topic = result.getString(4);
                    String detail = result.getString(5);
                    System.out.println(date+" "+time+" "+type+" "+ topic+" "+ detail);
                    schedule.addEvent(new Event(date,time,type, topic,detail));
                }
                conn.close();
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**remove event in Database*/
    public void deleteToDB(Event event){
        try{
            Connection conn = connectionDB();
            if (conn != null) {
                System.out.println("Connected to the database...");
                DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
                Statement statement = conn.createStatement();
                try{
                    String topic = (event.getTopic()).replace("'","\''");
                    String detail = (event.getDetail()).replace("'","\''");
                    String queryDelete = "delete from events "+"where(date='" + event.getDate()+"' and time='"+event.getTime()+"' and type='"+event.getType()+"' and topic='"+ topic +"' and detail='"+ detail +"')";
                    statement.executeUpdate(queryDelete);
                    System.out.println("delete-------");

                }catch (SQLException ex){
                    System.out.println("error of delete");}
                conn.close();
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**edit event in Database*/
    public void updateToDB(Event event) {
        try{
            Connection conn = connectionDB();
            if (conn != null) {
                System.out.println("Connected to the database...");
                Statement statement = conn.createStatement();
                try{
                    String topic = (event.getTopic()).replace("'","\''");
                    String detail = (event.getDetail()).replace("'","\''");

                    String topicOld = (event.getTopicOld()).replace("'","\''");
                    String detailOld = (event.getDetailOld()).replace("'","\''");
                    String queryUpdate = String.format("update events "+"set date='%s', time='%s', type='%s', topic='%s', detail='%s' "
                                    +"where date='%s' and time='%s' and type='%s' and topic='%s' and detail='%s'",
                            event.getDate(),event.getTime(), event.getType(),topic,detail,
                            event.getDateOld(),event.getTimeOld(), event.getTypeOld(),topicOld,detailOld);
                    statement.executeUpdate(queryUpdate);
                    System.out.println("edit-------");
                    event.setDateOld(event.getDate());
                    event.setTimeOld(event.getTime());
                    event.setTypeOld(event.getType());
                    event.setTopicOld(event.getTopic());
                    event.setDetailOld(event.getDetail());
                }catch (SQLException ex){
                    System.out.println("edit error");}
                conn.close();
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
