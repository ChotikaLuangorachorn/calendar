package models;

import java.util.Date;

public class Event {
	private String topic;
	private Date date;
	private Date time;
	private String detail;
	public Event(Date date,Date time,String topic, String detail) {

		this.date =  date;
		this.time = time;
		this.topic = topic;
		this.detail = detail;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	
}
