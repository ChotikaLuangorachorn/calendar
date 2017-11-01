/**  5810404928 Chotika Luangorachorn  */
package common.models;

import java.io.Serializable;

public class Event implements Serializable{

	private String topic;
	private String date;
	private String time;
	private String detail;
	private String type;
	private String topicOld;
	private String dateOld;
	private String timeOld;
	private String detailOld;
	private String typeOld;

	public Event(String date, String time, String type, String topic, String detail) {
		this.date =  date;
		this.time = time;
		this.topic = topic;
		this.detail = detail;
		this.dateOld =  date;
		this.timeOld = time;
		this.topicOld = topic;
		this.detailOld = detail;
		this.type = type;
		this.typeOld = type;
	}

	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topicOld = this.topic;
		this.topic = topic;
	}

	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.dateOld = this.date;
		this.date = date;
	}

	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.timeOld = this.time;
		this.time = time;
	}

	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detailOld = this.detail;
		this.detail = detail;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.typeOld = this.type;
		this.type = type;
	}

	public String getTopicOld() {
		return topicOld;
	}
	public String getDateOld() {
		return dateOld;
	}
	public String getTimeOld() {
		return timeOld;
	}
	public String getDetailOld() {
		return detailOld;
	}
	public String getTypeOld() {
		return typeOld;
	}


}
