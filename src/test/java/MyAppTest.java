//import static org.junit.Assert.*;
//
//import java.sql.*;
//import java.text.ParseException;
//
//import java.util.ArrayList;
//
//import org.junit.Test;
//
//import client.server.controllers.MainController;
//import common.models.Event;
//import common.models.Schedule;
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
//
//		Event eventTest = new Event("03/08/2017", "21:52", "Add", "JUnit test-Schedule");
//		ArrayList<Event> test = new ArrayList<Event>();
//		test.add(eventTest);
//		schedule.addEvent(eventTest);
//		assertEquals(schedule.getEvents(),test);
//	}
//
//    /** ---------- Test connect SQLite ---------- */
//
//	/** Test Save Button
//	 * and saveEvent method on MainControl class
//     */
//	@Test
//	public void testSaveBtn(){
//		MainController controller = new MainController();
//		controller.startCalendarApp();
//		Event eventTest = new Event("03/09/17", "12:29", "Save", "JUnit test-Button");
//		controller.saveEvent(eventTest);
//		assertTrue((controller.showSchedule()).contains(eventTest));
//	}
//
//    /** Test Edit Button and Confirm Edit Button */
//    @Test
//    public void testEditConfirmBtn(){
//        MainController controller = new MainController();
//        controller.startCalendarApp();
//        Event eventTest = new Event("02/09/17", "12:27", "test ?", "JUnit test-Button");
//        controller.saveEvent(eventTest);
//        eventTest.setTopic("Edit Confirm");
//        controller.editEvent(eventTest);
//        assertTrue((controller.showSchedule()).contains(eventTest));
//    }
//
//    /** Test Delete Button */
//    @Test
//    public void testDeleteBtn(){
//        MainController controller = new MainController();
//        controller.startCalendarApp();
//        Event eventTest = new Event("03/09/17", "12:32", "Delete", "JUnit test-Button");
//        controller.saveEvent(eventTest);
//
//        controller.removeEvent(eventTest);
//        ArrayList<Event>  eventsTest = new ArrayList<>();
//        try{
//            Class.forName("org.sqlite.JDBC");
//            String dbURL = "jdbc:sqlite:schedule.db";
//            Connection conn = DriverManager.getConnection(dbURL);
//            if (conn != null) {
//                System.out.println("Connected to the database...");
//                DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
//
//                Statement statement = conn.createStatement();
//                String querySelect = "select * from events";
//                ResultSet result =  statement.executeQuery(querySelect);
//                while (result.next()){
//                    String date = result.getString(1);
//                    String time = result.getString(2);
//                    String topic = result.getString(3);
//                    String detail = result.getString(4);
//                    eventsTest.add(new Event(date,time,topic,detail));
//                }
//                conn.close();
//            }
//        } catch (ClassNotFoundException ex) {
//            ex.printStackTrace();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        assertFalse( eventsTest.contains(eventTest));
//    }
//}
