/**  5810404928 Chotika Luangorachorn  */
package models;

public class Event {

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
		this.topic = topic;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTopicOld() {
		return topicOld;
	}

	public void setTopicOld(String topicOld) {
		this.topicOld = topicOld;
	}

	public String getDateOld() {
		return dateOld;
	}

	public void setDateOld(String dateOld) {
		this.dateOld = dateOld;
	}

	public String getTimeOld() {
		return timeOld;
	}

	public void setTimeOld(String timeOld) {
		this.timeOld = timeOld;
	}

	public String getDetailOld() {
		return detailOld;
	}

	public void setDetailOld(String detailOld) {
		this.detailOld = detailOld;
	}

	public String getTypeOld() {
		return typeOld;
	}

	public void setTypeOld(String typeOld) {
		this.typeOld = typeOld;
	}

}
