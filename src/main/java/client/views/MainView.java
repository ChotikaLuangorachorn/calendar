/**  5810404928 Chotika Luangorachorn  */
package client.views;


import client.controllers.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import common.models.Event;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainView implements Initializable {
	
	@FXML
	private TableView<Event> tableApp;
	@FXML
	private TableColumn<Event, String> dateCol, timeCol, typeCol, topicCol, detailCol;
	@FXML
	private DatePicker datePicker, searchPicker;
	@FXML
	private TextField topicText;
	@FXML
	private TextArea detailText;
	@FXML
	private Button confirmBtn;
	@FXML
	private Button saveBtn;
	@FXML
	private ComboBox typeBox, hourBox, minBox;
	private ObservableList<String> repeatTypeList = FXCollections.observableArrayList("Never","Daily", "Weekly", "Monthly", "Yearly");
	private ObservableList<String> hrList = FXCollections.observableArrayList();
	private ObservableList<String> minList = FXCollections.observableArrayList();
	private ObservableList<Event> data;
	private MainController controller;
	private Event eventSelect;
	private ArrayList<Event> events;

	public void setController(MainController controller){
		this.controller = controller;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		datePicker.setValue(LocalDate.now());
		typeBox.setValue("Never");
		typeBox.setItems(repeatTypeList);

		String hrs = "";
		for (int i=0; i<24;i++){
			hrs = i +"";
			if (hrs.length()<2){
				hrs = "0" + hrs;
			}
			hrList.add(hrs);
		}
		hourBox.setItems(hrList);

		String mins = "";
		for (int i=0; i<60;i++){
			mins = i +"";
			if (mins.length()<2){
				mins = "0" + mins;
			}
			minList.add(mins);
		}
		minBox.setItems(minList);

		initCol();
	}

	/** action of Save Button
	 * click for save event to Schedule */
	public void saveAppointment(ActionEvent event){
		String date = datePicker.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yy", Locale.ENGLISH));
		String day = datePicker.getValue().getDayOfWeek().toString();
		System.out.println(day);
		String type = typeBox.getValue().toString();
		String timeText = hourBox.getValue()+":"+ minBox.getValue();
		Event eventNow = new Event(date,timeText, type, topicText.getText(), detailText.getText());
		controller.saveEvent(eventNow);

		datePicker.setValue(LocalDate.now());
		topicText.setText("");
		detailText.setText("");
		typeBox.setValue("Never");
		hourBox.setValue("hr");
		minBox.setValue("min");

		if (!searchPicker.getEditor().getText().equals("")){
			this.search(new ActionEvent());}
		else {
			events = controller.getSchedule();
			initData(events);
		}
//		events = controller.getSchedule();
//		initData(events);
	}

	/** action of Delete Button
	 * click for remove event */
	public void deleteAppointment(ActionEvent event){
		eventSelect = tableApp.getSelectionModel().getSelectedItem();
		if (eventSelect != null) {
			tableApp.getItems().remove(eventSelect);
			controller.removeEvent(eventSelect);
			events = controller.getSchedule();
//			setEvents(controller.getSchedule());
			tableApp.getSelectionModel().clearSelection();
		}
	}

	/** action of Edit Button
	 * click for edit event */
	public void editAppointment(ActionEvent event){
		eventSelect = tableApp.getSelectionModel().getSelectedItem();

		if (eventSelect != null) {

			saveBtn.setDisable(true);
			topicText.setText(eventSelect.getTopic());
			detailText.setText(eventSelect.getDetail());
			hourBox.setValue((eventSelect.getTime().split(":"))[0]);
			minBox.setValue((eventSelect.getTime().split(":"))[1]);
//			datePicker.getEditor().setText(eventSelect.getDate());
			datePicker.setValue(local_date(eventSelect.getDate()));

			String type = eventSelect.getType();
			typeBox.setValue(type);
			confirmBtn.setDisable(false);
		}

	}
	public LocalDate local_date (String dateString){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
		LocalDate localDate = LocalDate.parse(dateString, formatter);
		return localDate;
	}

	/** action of Confirm Button
	 * click for confirm editing event */
	public void confirmEdit(ActionEvent event){
		String date = datePicker.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yy", Locale.ENGLISH));
		System.out.println(date);
		String topic = topicText.getText();
		String time = hourBox.getValue() + ":" + minBox.getValue();
		String type = typeBox.getValue().toString();
		String detail = detailText.getText();
//		eventSelect.setDate(date);
//		eventSelect.setTime(hourBox.getValue() + ":" + minBox.getValue());
//		eventSelect.setTopic(topicText.getText());
//		eventSelect.setDetail(detailText.getText());
//		eventSelect.setType(typeBox.getValue().toString());
//		controller.editEvent(eventSelect);
		eventSelect.setDate(date);
		eventSelect.setTime(time);
		eventSelect.setTopic(topic);
		eventSelect.setDetail(detail);
		eventSelect.setType(type);
		controller.editEvent(eventSelect);
		if (!searchPicker.getEditor().getText().equals("")){
			this.search(new ActionEvent());}
		else {
			events = controller.getSchedule();
			initData(events);}

		datePicker.setValue(LocalDate.now());
		hourBox.setValue("hr");
		minBox.setValue("min");
		topicText.setText("");
		detailText.setText("");
		typeBox.setValue("Never");
		confirmBtn.setDisable(true);
		saveBtn.setDisable(false);
		tableApp.getSelectionModel().clearSelection();

	}

	/**Search Event
	* */
	public void search(ActionEvent event){
		events = controller.getSchedule();
		if (searchPicker.getValue() != null) {
			System.out.println("search...");
			Date date = Date.from((searchPicker.getValue()).atStartOfDay(ZoneId.systemDefault()).toInstant());
			events = controller.searchEvent(date);
			initData(events);
		}

	}
	/**
	 * click for show all Event*/
	public void showAllAppointment(ActionEvent event){
		events = controller.getSchedule();
		initData(events);
		searchPicker.setValue(null);
	}

	/**set column of Schedule appointment that save all events*/
	private void initCol(){
		dateCol.setCellValueFactory(new PropertyValueFactory<Event, String>("date"));
		timeCol.setCellValueFactory(new PropertyValueFactory<Event,String>("time"));
		typeCol.setCellValueFactory(new PropertyValueFactory<Event, String>("type"));
		topicCol.setCellValueFactory(new PropertyValueFactory<Event, String>("topic"));
		detailCol.setCellValueFactory(new PropertyValueFactory<Event, String>("detail"));
	}

	/**
	 * set items of table
	 * @param events
	 */	

	private void initData(ArrayList<Event> events){
		data = FXCollections.observableList(events);
		tableApp.setItems(data);
	}
//
//	public TableView<Event> getTableApp() {
//		return tableApp;
//	}

	public void setEvents(ArrayList<Event> events) {
		this.events = events;
		ObservableList<Event> data = FXCollections.observableList(events);
		tableApp.setItems(data);
		searchPicker.getEditor().setText("");
	}
}
