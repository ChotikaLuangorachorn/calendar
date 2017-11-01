/**  5810404928 Chotika Luangorachorn  */
package client.controllers;

import java.util.ArrayList;
import java.util.Date;

import common.DBService;
import common.ScheduleServer;
import common.models.Event;
import common.models.Schedule;
import server.controllers.DBController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import client.views.MainView;

public class MainController{
	private MainView view;
	private ScheduleServer schedule;
	private DBService db;

	public void startCalendarApp()  {
		this.view = new MainView();
//		this.schedule = new Schedule();
//		this.db = new DbSchedule.xml(schedule);
		ApplicationContext bean = new ClassPathXmlApplicationContext("client.xml");
		db = (DBService) bean.getBean("DBManager");
		schedule = (ScheduleServer) bean.getBean("ScheduleManager");
		this.db.selectToDB();
	}
	
	/**show Schedule or all events
	It will called when click Save button*/
	public ArrayList<Event> getSchedule(){
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

