import controllers.MainController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Event;
import views.MainView;

import java.io.IOException;

/**
 * Hello world!
 *
 */
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

		ObservableList<Event> data = FXCollections.observableList(controller.showSchedule());
		mainView.getTableApp().setItems(data);

		primaryStage.setTitle("Appointment");
		primaryStage.setScene(new Scene(root));
		primaryStage.setResizable(false);
		primaryStage.show();
		
	}

}
