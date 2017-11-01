package client; /**  5810404928 Chotika Luangorachorn  */

import client.controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import client.views.MainView;

import java.io.IOException;


public class StartApp extends Application {

    public static void main( String[] args ) {
    	launch(args);
    }

	@Override
	public void start(Stage primaryStage) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/MainView.fxml"));
		Parent root = loader.load();

		MainController controller = new MainController();
		controller.startCalendarApp();

		MainView mainView = loader.getController();
		mainView.setController(controller);

//		ObservableList<Event> data = FXCollections.observableList(controller.getSchedule());
//
//		mainView.getTableApp().setItems(data);
		mainView.setEvents(controller.getSchedule());

		primaryStage.setTitle("Appointment");
		primaryStage.setScene(new Scene(root));
		primaryStage.setResizable(false);
		primaryStage.show();
		
	}

}
