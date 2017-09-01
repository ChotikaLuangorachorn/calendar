import controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import views.MainView;

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
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/MainView.fxml"));
		Parent root = loader.load();
		
		MainController controller = new MainController();
		controller.startCalendarApp();
		
		System.out.println(loader.getController()+"");
		MainView mainView = loader.getController();
		mainView.setController(controller);
		
		primaryStage.setTitle("Appointment");
		primaryStage.setScene(new Scene(root));
		primaryStage.setResizable(false);
		primaryStage.show();
		
		
		
		
	}
}
