/**  5810404928 Chotika Luangorachorn  */
package controllers;

import java.util.ArrayList;
import java.util.Date;

import models.Event;
import models.Schedule;
import views.MainView;

public class MainController{
	private MainView view;
	private Schedule schedule;
	private DBController db;

	public void startCalendarApp()  {
		this.view = new MainView();
		this.schedule = new Schedule();
		this.db = new DBController(schedule);
		this.db.selectToDB();
	}
	
	/**show Schedule or all events
	It will called when click Save button*/
	public ArrayList<Event> showSchedule(){
		ArrayList<Event> events = schedule.getEvents();
		return events;
	}

	/**add event to schedule in Schedule class
	when click Save button*/
	public void saveEvent(Event event){
		db.insertToDB(event);
	}

	/**remove event from schedule in Schedule class
	 when click Delete button*/
	public void removeEvent(Event event){
		db.deleteToDB(event);
	}

	/**edit event in schedule
	 when click Edit button*/
	public void editEvent(Event event){ db.updateToDB(event);}

	public ArrayList<Event> searchEvent(Date date){
		return schedule.getEventsSearch(date);
	}

}

