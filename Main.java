import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import ru.pflb.mq.dummy.implementation.ConnectionImpl;
import ru.pflb.mq.dummy.implementation.DestinationImpl;
import ru.pflb.mq.dummy.implementation.ProducerImpl;
import ru.pflb.mq.dummy.implementation.SessionImpl;


public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ConnectionImpl connection = new ConnectionImpl();
        connection.start();
        connection.createSession(true);
        SessionImpl session = new SessionImpl();
        String с = "myQueue";
        DestinationImpl destination = new DestinationImpl(с);
        ProducerImpl producer = new ProducerImpl(destination);
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(args[0]));
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            for (String s : lines) {
                producer.send(s);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        //session.close();
        //connection.close();
    }
}