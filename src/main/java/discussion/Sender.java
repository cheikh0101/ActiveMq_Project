package discussion;

import javax.jms.JMSException;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Sender {

	private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    
    private static String subject = "JCG_QUEUE";
    
    public static void main(String[] args) throws JMSException {  
    	
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        
        Connection myConnection = connectionFactory.createConnection();
        
        myConnection.start();
        
        Session mySession = myConnection.createSession(false,
                Session.AUTO_ACKNOWLEDGE);  
        
        Destination destination = mySession.createQueue(subject); 
        
        MessageProducer producer = mySession.createProducer(destination);
         
        TextMessage myMessage = mySession
                .createTextMessage("Bienvenue dans ce petit chat...");
        producer.send(myMessage);
         
        System.out.println("Emetteur : " + myMessage.getText() + "'");
        myConnection.close();
    }
    
}
