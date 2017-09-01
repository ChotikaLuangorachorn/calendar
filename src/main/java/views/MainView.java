package views;

import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import controllers.MainController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import models.Event;
import models.Schedule;


public class MainView implements Initializable{
	
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
	private DatePicker dateText;
	@FXML
	private TextField timeText;
	@FXML
	private TextField topicText;
	@FXML
	private TextArea detailText;
	
	private ObservableList<Event> data;
	private MainController controller;
	private SimpleDateFormat dateFormat;
	private SimpleDateFormat timeFormat;
	
	
	public MainView() {
		this.dateFormat = new SimpleDateFormat("dd/MM/yy");
		this.timeFormat = new SimpleDateFormat("HH:mm");
	}
	
	public void setController(MainController controller){
		this.controller = controller;
	}
	
	public void initialize(URL location, ResourceBundle resources) {
		dateText.setValue(LocalDate.now());
		initCol();
	}
	
	/** action of Save Button
	 * click for save event to Schedule */
	public void saveAppointment(ActionEvent event) throws ParseException{
		String date = dateText.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yy"));
		Event eventNow = new Event(dateFormat.parse(date), timeFormat.parse(timeText.getText()), topicText.getText(), detailText.getText());
		
		controller.saveEvent(eventNow);
		ArrayList<Event> events = controller.showSchedule();
		initData(events);
	}
	
	/**set column of Schedule appointment that save all events*/
	private void initCol(){
		dateCol.setCellValueFactory(new Callback<CellDataFeatures<Event,String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Event, String> param) {
				SimpleStringProperty property = new SimpleStringProperty();
				DateFormat formatter = new SimpleDateFormat("dd/MM/yy");
				property.setValue(formatter.format(param.getValue().getDate()));
				return property;
			}
		});
		timeCol.setCellValueFactory(new Callback<CellDataFeatures<Event,String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Event, String> param) {
				SimpleStringProperty property = new SimpleStringProperty();
				DateFormat formatter = new SimpleDateFormat("HH:mm");
				property.setValue(formatter.format(param.getValue().getTime()));
				return property;
			}
		});
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

}
