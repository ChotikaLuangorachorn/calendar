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
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.TimeZone;

public class MainView implements Initializable {
	
	@FXML
	private TableView<Event> tableApp;
	@FXML
	private TableColumn<Event, String> dateCol;
	@FXML
	private TableColumn<Event, String> timeCol;
	@FXML
	private TableColumn<Event, String> topicCol;
	@FXML
	private TableColumn<Event, String> detailCol;
	@FXML
	private DatePicker datePicker;
	@FXML
	private TextField timeText;
	@FXML
	private TextField topicText;
	@FXML
	private TextArea detailText;
	@FXML
	private Button confirmBtn;
	@FXML
	private Button saveBtn;
	
	private ObservableList<Event> data;
	private MainController controller;

	public void setController(MainController controller){
		this.controller = controller;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		datePicker.setValue(LocalDate.now());
		initCol();
	}

	/** action of Save Button
	 * click for save event to Schedule */
	public void saveAppointment(ActionEvent event){
		String date = datePicker.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yy"));
		Event eventNow = new Event(date,timeText.getText(), topicText.getText(), detailText.getText());
		controller.saveEvent(eventNow);
		ArrayList<Event> events = controller.showSchedule();
		initData(events);
	}
	public void deleteAppointment(ActionEvent event){
		Event eventSelect = tableApp.getSelectionModel().getSelectedItem();
		if (eventSelect != null) {
			tableApp.getItems().remove(eventSelect);
			controller.removeEvent(eventSelect);
		}
	}
	public void editAppointment(ActionEvent event){
		Event eventSelect = tableApp.getSelectionModel().getSelectedItem();
		if (eventSelect != null) {
			saveBtn.setDisable(true);
			topicText.setText(eventSelect.getTopic());
			detailText.setText(eventSelect.getDetail());
			timeText.setText(eventSelect.getTime());
			datePicker.getEditor().setText(eventSelect.getDate());

			confirmBtn.setDisable(false);
		}
	}
	public  void confirmEdit(ActionEvent event){
		Event eventSelect = tableApp.getSelectionModel().getSelectedItem();
		String date = datePicker.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yy"));
		eventSelect.setDate(date);
		eventSelect.setTime(timeText.getText());
		eventSelect.setTopic(topicText.getText());
		eventSelect.setDetail(detailText.getText());
		controller.editEvent(eventSelect);
		datePicker.setValue(LocalDate.now());
		timeText.setText("00:00");
		topicText.setText("");
		detailText.setText("");

		confirmBtn.setDisable(true);
		saveBtn.setDisable(false);
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
