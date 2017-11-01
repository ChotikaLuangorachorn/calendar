package server;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by PC301 on 1/11/2560.
 */
public class MainServer {
    public static void main(String[] args) {
        ApplicationContext bf =
                new ClassPathXmlApplicationContext("server.xml");

        System.out.println("Server is ready.");
    }
}
