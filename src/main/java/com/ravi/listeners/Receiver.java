package com.ravi.listeners;

import static com.ravi.constants.AppConstants.DESTINATION_QUEUE_1;
import static com.ravi.constants.AppConstants.DESTINATION_QUEUE_2;
import static com.ravi.constants.AppConstants.SOURCE_TOPIC;
import static com.ravi.constants.AppConstants.SUBSCRIPTION_ID;

import java.io.IOException;

import com.ravi.dao.DBRepo;
import com.ravi.model.SwiftMsg;
import com.ravi.util.AppUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    @Autowired
    private DBRepo dao;

    @JmsListener(destination = DESTINATION_QUEUE_1, containerFactory = "queueListenerFactory")
    public void receiveMessageFromDest1(String msg) {
        System.out.println("received message" + msg);
        this.reconcileMsg(msg, DESTINATION_QUEUE_1);
    }

    @JmsListener(destination = DESTINATION_QUEUE_2, containerFactory = "queueListenerFactory")
    public void receiveMessageFromDest2(String msg) {
        System.out.println("received message" + msg);
        this.reconcileMsg(msg, DESTINATION_QUEUE_2);
    }

    @JmsListener(destination = SOURCE_TOPIC ,id = SUBSCRIPTION_ID ,containerFactory = "topicListenerFactory", subscription = SOURCE_TOPIC)
    public void devSubscriber(String msg) {
        System.out.println("received message" + msg);
        try {
            SwiftMsg swmsg = AppUtil.getSwiftMsg(msg, SOURCE_TOPIC);
            dao.saveMsg(swmsg);
        } catch (IOException e) {
            // TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }

    private void reconcileMsg(String msg, String src) {
        SwiftMsg swmsg = null;
        try {
            swmsg = AppUtil.getSwiftMsg(msg, src);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        int count = dao.reconcileMsg(swmsg);
        if (count < 1) {
            dao.saveUnreconciledMsg(swmsg);
        }

    }


}
