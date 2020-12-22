import ru.pflb.mq.dummy.implementation.ConnectionImpl;
import ru.pflb.mq.dummy.implementation.DestinationImpl;
import ru.pflb.mq.dummy.implementation.ProducerImpl;
import ru.pflb.mq.dummy.implementation.SessionImpl;




public  class Task_1 {
    public static void main(String[] args) {
        ConnectionImpl connection = new ConnectionImpl();
        connection.start();
        connection.createSession(true);
        SessionImpl session = new SessionImpl();
        String s = "myQueue";
        DestinationImpl destination = new DestinationImpl(s);
        ProducerImpl producer = new ProducerImpl(destination);
        String[] oneTwo = {"Раз", "Два", "Три"};
        for (String value : oneTwo) {
            producer.send(value);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        session.close();
        connection.close();
    }
}
