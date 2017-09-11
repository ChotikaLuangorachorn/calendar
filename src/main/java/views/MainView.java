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
	private TableColumn<Event, String> dateCol, timeCol, typeCol, topicCol, detailCol;
	@FXML
	private DatePicker datePicker;
	@FXML
	private TextField timeText, topicText;
	@FXML
	private TextArea detailText;
	@FXML
	private Button confirmBtn;
	@FXML
	private Button saveBtn;
	@FXML
	private RadioButton dailyBtn, weeklyBtn, monthlyBtn, yearlyBtn;
	@FXML
	private ToggleGroup typeGroup;

	
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
		String day = datePicker.getValue().getDayOfWeek().toString();
		System.out.println(day);
		String type = "0000";
		if (dailyBtn.isSelected()){
			type = dailyBtn.getText();}
		else if (weeklyBtn.isSelected()){
			type = weeklyBtn.getText();}
		else if (monthlyBtn.isSelected()){
			type = monthlyBtn.getText();}
		else if (yearlyBtn.isSelected()){
			type = yearlyBtn.getText();}
		Event eventNow = new Event(day +" "+date,timeText.getText(), type, topicText.getText(), detailText.getText());
		controller.saveEvent(eventNow);

		datePicker.setValue(LocalDate.now());
		timeText.setText("00:00");
		topicText.setText("");
		detailText.setText("");
		dailyBtn.setSelected(true);

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
			timeText.setText(eventSelect.getTime());
			datePicker.getEditor().setText(eventSelect.getDate());
			String type = eventSelect.getType();
			dailyBtn.setSelected(false);
			weeklyBtn.setSelected(false);
			monthlyBtn.setSelected(false);
			yearlyBtn.setSelected(false);
			if (type.equals(dailyBtn.getText())){
				dailyBtn.setSelected(true);}
			else if (type.equals(weeklyBtn.getText())){
				weeklyBtn.setSelected(true);}
			else if (type.equals(monthlyBtn.getText())){
				monthlyBtn.setSelected(true);}
			else if (type.equals(yearlyBtn.getText())){
				yearlyBtn.setSelected(true);}

			confirmBtn.setDisable(false);
		}

	}

	/** action of Confirm Button
	 * click for confirm editing event */
	public  void confirmEdit(ActionEvent event){
		Event eventSelect = tableApp.getSelectionModel().getSelectedItem();
		String date = datePicker.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yy"));
		eventSelect.setDate(date);
		eventSelect.setTime(timeText.getText());
		eventSelect.setTopic(topicText.getText());
		eventSelect.setDetail(detailText.getText());
		if (dailyBtn.isSelected()){
			eventSelect.setType(dailyBtn.getText());}
		else if (weeklyBtn.isSelected()){
			eventSelect.setType(weeklyBtn.getText());}
		else if (monthlyBtn.isSelected()){
			eventSelect.setType(monthlyBtn.getText());}
		else if (yearlyBtn.isSelected()){
			eventSelect.setType(yearlyBtn.getText());}

		controller.editEvent(eventSelect);

		datePicker.setValue(LocalDate.now());
		timeText.setText("00:00");
		topicText.setText("");
		detailText.setText("");
		dailyBtn.setSelected(true);

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
