package models;

import java.util.ArrayList;

public class Schedule {
	private ArrayList<Event> events;
	private Event event;
	public Schedule() {
		this.events = new ArrayList<Event>();
	}
	
	/** add each even to Schedule list 
	 * or Array List:events*/
	public void addEvent(Event event){
		this.events.add(event);
	}
	
	public ArrayList<Event> getEvents() {
		return events;
	}
	public Event getEvent() {
		return event;
	}

}
