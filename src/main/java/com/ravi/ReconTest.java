package com.ravi;

import static com.ravi.constants.AppConstants.DESTINATION_QUEUE_1;
import static com.ravi.constants.AppConstants.DESTINATION_QUEUE_2;
import static com.ravi.constants.AppConstants.SOURCE_TOPIC;

import javax.annotation.PostConstruct;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import com.ravi.util.AppUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

@SpringBootApplication
@EnableJms
public class ReconTest implements CommandLineRunner{
    
    @Autowired
    private ConnectionFactory connectionFactory;

    @Autowired
    private JmsTemplate jmsTopicTemplate;

    @Autowired
    private JmsTemplate jmsQueueTemplate;

    public static void main(String[] args) {
        SpringApplication.run(ReconTest.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        testRecon();
    }

    @PostConstruct
    public void init() {
        this.jmsTopicTemplate = new JmsTemplate(connectionFactory);
        this.jmsTopicTemplate.setPubSubDomain(true);

        this.jmsQueueTemplate = new JmsTemplate(connectionFactory);
    } 

	public void testRecon() throws Exception {

        /**
         * Test scenario 
         * SOURCE topic messages need to be reconciled with messages from Q1 and Q2
         * 
         * msgFromQ1 --> published in dev/ topic also Queue1
         * msgFromQ2 --> sent through Queue 2
         * 
         * So msgFromQ1 should be reconciled and msgFromQ2 will be unreconciled 
         * 
         */

        String msgFromQ1 = AppUtil.readFileByName("/mt103.txt");
        String msgFromQ2 = AppUtil.readFileByName("/mt320.txt");
        
        // this is message from source system
        this.jmsTopicTemplate.send(SOURCE_TOPIC, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(msgFromQ1);
            }
        });

        // adding lag for replication the realworld scenario
        Thread.sleep(5000);

        //message from target 1
		this.jmsQueueTemplate.send(DESTINATION_QUEUE_1, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(msgFromQ1);
            }
        });

        //message from target 2
        this.jmsQueueTemplate.send(DESTINATION_QUEUE_2, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(msgFromQ2);
            }
        });
    }
}