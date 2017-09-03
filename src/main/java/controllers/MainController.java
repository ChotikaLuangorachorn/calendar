package controllers;


import java.util.ArrayList;
import models.Event;
import models.Schedule;
import views.MainView;

public class MainController{
	private MainView view;
	private Schedule schedule;

	public void startCalendarApp()  {
		this.view = new MainView();
		this.schedule = new Schedule();
		this.schedule.selectToDB();
	}
	
/**show Schedule or all events
	It will called when click Save button*/
	public ArrayList<Event> showSchedule(){
		ArrayList<Event> events = schedule.getEvents();
		return events;
	}
	
	/**add event to schedule in Schedule class
	when click Save button*/
	public void saveEvent(Event eventNow){
		schedule.addEvent(eventNow);
	}
	/**remove event from schedule in Schedule class
	 when click Delete button*/
	public void removeEvent(Event event){
		schedule.deleteEvent(event);
	}
	/**edit event in schedule
	 when click Edit button*/
	public void editEvent(Event event){ schedule.updateToDB(event);}

}

