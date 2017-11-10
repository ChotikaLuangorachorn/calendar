package server;
/**  5810404928 Chotika Luangorachorn  */
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainServer {
    public static void main(String[] args) {
        ApplicationContext bf =
                new ClassPathXmlApplicationContext("server.xml");

        System.out.println("Server is ready.");
    }
}
