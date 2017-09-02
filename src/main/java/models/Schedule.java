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
	
	/** add each even to Schedule list 
	 * or Array List:events*/
	public void addEvent(Event event) {
		this.insertToDB(event);
	}
	public void deleteEvent(Event event) {
		this.deleteToDB(event);
	}
	public ArrayList<Event> getEvents() {
		return events;
	}
	public Event getEvent() {
		return event;
	}

	public void insertToDB(Event event) {
		try{
			Class.forName("org.sqlite.JDBC");
			String dbURL = "jdbc:sqlite:schedule.db";
			Connection conn = DriverManager.getConnection(dbURL);
			if (conn != null) {
				System.out.println("Connected to the database...");
				Statement statement = conn.createStatement();
				try{
				String queryInsert = "insert into events "+"values('" + event.getDate()+"','"+event.getTime()+"','"+event.getTopic()+"','"+event.getDetail()+"')";
				statement.executeUpdate(queryInsert);
				System.out.println("up-------");
				this.events.add(event);
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
					String queryInsert = "delete from events "+"where(date='" + event.getDate()+"' and time='"+event.getTime()+"' and topic='"+event.getTopic()+"' and detail='"+event.getDetail()+"')";
					statement.executeUpdate(queryInsert);
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
	public void updateToDB(Event event) {
		try{
			Class.forName("org.sqlite.JDBC");
			String dbURL = "jdbc:sqlite:schedule.db";
			Connection conn = DriverManager.getConnection(dbURL);
			if (conn != null) {
				System.out.println("Connected to the database...");
				Statement statement = conn.createStatement();
				try{
					String queryInsert = String.format("update events "+"set date='%s', time='%s', topic='%s', detail='%s' "
							+"where date='%s' and time='%s' and topic='%s' and detail='%s'",
							event.getDate(),event.getTime(),event.getTopic(),event.getDetail(),
							event.getDateOld(),event.getTimeOld(),event.getTopicOld(),event.getDetailOld());
					statement.executeUpdate(queryInsert);
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
