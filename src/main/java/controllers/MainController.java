package controllers;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import models.Event;
import models.Schedule;
import views.MainView;

public class MainController{
	private MainView view;
	private Event event;
	private Schedule schedule;
	private DataController data;

	public void startCalendarApp(){
		this.view = new MainView();
		this.schedule = new Schedule();

	}
	public ArrayList<Event> showSchedule(){
		ArrayList<Event> events = schedule.getEvents();
		return events;
	}
	public void saveEvent(Event eventNow){
		schedule.addEvent(eventNow);
	}

}
