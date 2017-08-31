package controllers;


import java.util.ArrayList;

import models.Event;
import models.Schedule;
import views.MainView;

public class MainController{
	private MainView view;
	private Event event;
	private Schedule schedule;
	private Event eventOld;

	public void startCalendarApp(){
		this.view = new MainView();
		this.schedule = new Schedule();

	}
	
/**showScheedule or all events
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

public Schedule getSchedule() {
	return schedule;
}

}
