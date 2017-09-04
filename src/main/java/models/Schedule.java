package models;

import java.sql.*;
import java.util.ArrayList;
import java.sql.SQLException;

public class Schedule {
	private ArrayList<Event> events;
	private Event event;
	public Schedule(){
		this.events = new ArrayList<Event>();
	}
	
	/** add each event to Schedule list
	 * or Array List:events*/
	public void addEvent(Event event) {
		this.events.add(event);
	}
	public void deleteEvent(Event event) {
		this.deleteToDB(event);
	}
	public ArrayList<Event> getEvents() {
		return events;
	}

	/**add event to Database*/
	public void insertToDB(Event event) {
		try{
			Class.forName("org.sqlite.JDBC");
			String dbURL = "jdbc:sqlite:schedule.db";
			Connection conn = DriverManager.getConnection(dbURL);
			if (conn != null) {
				System.out.println("Connected to the database...");
				Statement statement = conn.createStatement();
				try{
					String topic = (event.getTopic()).replace("'","\''");
					String detail = (event.getDetail()).replace("'","\''");
					String queryInsert = "insert into events "+"values('" + event.getDate()+"','"+event.getTime()+"','"+topic+"','"+detail+"')";
					statement.executeUpdate(queryInsert);
					System.out.println("up-------");
					this.addEvent(event);
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
			Class.forName("org.sqlite.JDBC");
			String dbURL = "jdbc:sqlite:schedule.db";
			Connection conn = DriverManager.getConnection(dbURL);
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
					String topic = result.getString(3);
					String detail = result.getString(4);
					System.out.println(date+" "+time+" "+ topic+" "+ detail);
					events.add(new Event(date,time,topic,detail));
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
			Class.forName("org.sqlite.JDBC");
			String dbURL = "jdbc:sqlite:schedule.db";
			Connection conn = DriverManager.getConnection(dbURL);
			if (conn != null) {
				System.out.println("Connected to the database...");
				DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
				Statement statement = conn.createStatement();
				try{
					String topic = (event.getTopic()).replace("'","\''");
					String detail = (event.getDetail()).replace("'","\''");
					String queryDelete = "delete from events "+"where(date='" + event.getDate()+"' and time='"+event.getTime()+"' and topic='"+ topic +"' and detail='"+ detail +"')";
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
			Class.forName("org.sqlite.JDBC");
			String dbURL = "jdbc:sqlite:schedule.db";
			Connection conn = DriverManager.getConnection(dbURL);
			if (conn != null) {
				System.out.println("Connected to the database...");
				Statement statement = conn.createStatement();
				try{
					String topic = (event.getTopic()).replace("'","\''");
					String detail = (event.getDetail()).replace("'","\''");

					String topicOld = (event.getTopicOld()).replace("'","\''");
					String detailOld = (event.getDetailOld()).replace("'","\''");
					String queryUpdate = String.format("update events "+"set date='%s', time='%s', topic='%s', detail='%s' "
							+"where date='%s' and time='%s' and topic='%s' and detail='%s'",
							event.getDate(),event.getTime(),topic,detail,
							event.getDateOld(),event.getTimeOld(),topicOld,detailOld);
					statement.executeUpdate(queryUpdate);
					System.out.println("edit-------");
					event.setDateOld(event.getDate());
					event.setTimeOld(event.getTime());
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
}
