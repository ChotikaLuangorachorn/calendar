package views;


import controllers.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Event;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ValueRange;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class MainView implements Initializable {
	
	@FXML
	private TableView<Event> tableApp;
	@FXML
	private TableColumn<Event, String> dateCol, timeCol, typeCol, topicCol, detailCol;
	@FXML
	private DatePicker datePicker;
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
	private ObservableList<String> repeatTypeList = FXCollections.observableArrayList("Never","Daily", "Weekly", "Monthly");
	private ObservableList<String> hrList = FXCollections.observableArrayList();
	private ObservableList<String> minList = FXCollections.observableArrayList();
	private ObservableList<Event> data;
	private MainController controller;

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
		String date = datePicker.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yy"));
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

		ArrayList<Event> events = controller.showSchedule();
		initData(events);
	}

	/** action of Delete Button
	 * click for remove event */
	public void deleteAppointment(ActionEvent event){
		Event eventSelect = tableApp.getSelectionModel().getSelectedItem();
		if (eventSelect != null) {
			tableApp.getItems().remove(eventSelect);
			controller.removeEvent(eventSelect);
			tableApp.getSelectionModel().clearSelection();
		}
	}

	/** action of Edit Button
	 * click for edit event */
	public void editAppointment(ActionEvent event){
		Event eventSelect = tableApp.getSelectionModel().getSelectedItem();
		if (eventSelect != null) {
			saveBtn.setDisable(true);
			topicText.setText(eventSelect.getTopic());
			detailText.setText(eventSelect.getDetail());
			hourBox.setValue((eventSelect.getTime().split(":"))[0]);
			minBox.setValue((eventSelect.getTime().split(":"))[1]);
			datePicker.getEditor().setText(eventSelect.getDate());
			String type = eventSelect.getType();
			typeBox.setValue(type);
			confirmBtn.setDisable(false);
		}

	}

	/** action of Confirm Button
	 * click for confirm editing event */
	public  void confirmEdit(ActionEvent event){
		Event eventSelect = tableApp.getSelectionModel().getSelectedItem();
		String date = datePicker.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yy"));
		eventSelect.setDate(date);
		eventSelect.setTime(hourBox.getValue() + ":" + minBox.getValue());
		eventSelect.setTopic(topicText.getText());
		eventSelect.setDetail(detailText.getText());
		eventSelect.setType(typeBox.getValue().toString());

		controller.editEvent(eventSelect);
		datePicker.setValue(LocalDate.now());
		hourBox.setValue("hr");
		minBox.setValue("min");
		topicText.setText("");
		detailText.setText("");
		typeBox.setValue("Never");
		confirmBtn.setDisable(true);
		saveBtn.setDisable(false);
		tableApp.getSelectionModel().clearSelection();
		tableApp.refresh();
	}

	/**set column of Schedule appointment that save all events*/
	private void initCol(){
//		dateCol.setCellValueFactory(new Callback<CellDataFeatures<Event,String>, ObservableValue<String>>() {
//			public ObservableValue<String> call(CellDataFeatures<Event, String> param) {
//				SimpleStringProperty property = new SimpleStringProperty();
//				DateFormat formatter = new SimpleDateFormat("dd/MM/yy");
//				property.setValue(formatter.format(param.getValue().getDate()));
//				return property;
//			}
//		});
//		timeCol.setCellValueFactory(new Callback<CellDataFeatures<Event,String>, ObservableValue<String>>() {
//			public ObservableValue<String> call(CellDataFeatures<Event, String> param) {
//				SimpleStringProperty property = new SimpleStringProperty();
//				DateFormat formatter = new SimpleDateFormat("HH:mm");
//				property.setValue(formatter.format(param.getValue().getTime()));
//				return property;
//			}
//		});
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
	public TableView<Event> getTableApp() {
		return tableApp;
	}


}
