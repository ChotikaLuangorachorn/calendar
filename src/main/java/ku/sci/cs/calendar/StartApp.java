package ku.sci.cs.calendar;

import java.text.ParseException;

import controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class StartApp extends Application{

    public static void main( String[] args ) {
    	launch(args);
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/views/MainView.fxml"));

		primaryStage.setTitle("Appointment");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
		
		MainController controller = new MainController();
		controller.startCalendarApp();
		
		
	}
}
