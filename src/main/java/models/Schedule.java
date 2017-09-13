/**  5810404928 Chotika Luangorachorn  */
package models;

import java.util.ArrayList;

public class Schedule {
	private ArrayList<Event> events;
	public Schedule(){
		this.events = new ArrayList<Event>();
	}
	
	/** add each event to Schedule list
	 * or Array List:events*/
	public void addEvent(Event event) {
		this.events.add(event);
	}
	public ArrayList<Event> getEvents() {
		return events;
	}

}
