/**  5810404928 Chotika Luangorachorn  */
package models;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

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
	/** search event is match from schedule
	 * */
	public ArrayList<Event> getEventsSearch(Date dateSearch){
		ArrayList<Event> events = new ArrayList<Event>();
		DateFormat format = new SimpleDateFormat("dd/MM/yy", Locale.ENGLISH);

		try {
			for(Event event: this.events){
				Date date = format.parse(event.getDate());
				System.out.println("date = " + date);
				System.out.println("daySearch = " + dateSearch);
				if (date.getDate() == dateSearch.getDate() && date.getMonth() == dateSearch.getMonth() && date.getYear() == dateSearch.getYear()){
					events.add(event);
				}else if (event.getType().equals("Daily")){
					events.add(event);
				}else if (event.getType().equals("Weekly") && date.getDay() == dateSearch.getDay()){
					events.add(event);
				}else if (event.getType().equals("Monthly") && date.getDate() == dateSearch.getDate()){
					events.add(event);
				}else if (event.getType().equals("Yearly") && date.getDate() == dateSearch.getDate() && date.getMonth() == dateSearch.getMonth()){
					events.add(event);
				}
			}
		} catch (ParseException e) {
			System.out.println("XX Searching ERROR XX");
		}

		return events;
	}

}
