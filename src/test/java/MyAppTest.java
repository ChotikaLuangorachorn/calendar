//import static org.junit.Assert.*;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//
//import java.util.ArrayList;
//
//import org.junit.Test;
//
//import controllers.MainController;
//import models.Event;
//import models.Schedule;
//
//public class MyAppTest {
//
//	/**Test showSchedule method to return as not null.*/
//	@Test
//	public void testShowSchedule() {
//		MainController controller = new MainController();
//		controller.startCalendarApp();
//		assertNotNull(controller.showSchedule());
//	}
//
//	/**Test adding event to Schedule class
//	 * @throws ParseException */
//	@Test
//	public void testAddEvent() throws ParseException {
//		Schedule schedule = new Schedule();
//		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
//		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
//		Event eventTest = new Event(dateFormat.parse("31/8/2560"), timeFormat.parse("21:52"), "Test", "JUnit");
//		ArrayList<Event> test = new ArrayList<Event>();
//		test.add(eventTest);
//		schedule.addEvent(eventTest);
//		assertEquals(schedule.getEvents(),test);
//	}
//	/** Test Save Button
//	 * and saveEvent method on MainControl class
//	 * @throws ParseException */
//	@Test
//	public void testSaveBtn() throws ParseException {
//		MainController controller = new MainController();
//		controller.startCalendarApp();
//		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
//		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
//		Event eventTest = new Event(dateFormat.parse("31/8/2560"), timeFormat.parse("21:52"), "Test", "JUnit");
//		ArrayList<Event> test = new ArrayList<Event>();
//		test.add(eventTest);
//		controller.saveEvent(eventTest);
//		assertEquals(controller.getSchedule().getEvents(),test);
//	}
//}
