package com.ravi.config;

import static com.ravi.constants.AppConstants.SUBSCRIPTION_ID;

import javax.jms.ConnectionFactory;

import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

@Configuration
public class IBMMQConfig {

    @Bean
    public JmsListenerContainerFactory<?> queueListenerFactory(ConnectionFactory connectionFactory,
                                                    DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        // This provides all boot's default to this factory, including the message converter
                                                  
        configurer.configure(factory, connectionFactory);
        factory.setErrorHandler(t -> System.err.println("An error has occurred in the transaction" + t.getMessage()));
       // factory.setMessageConverter(jacksonJmsMessageConverter());      
       
        // You could still override some of Boot's default if necessary.
        return factory;
    }

    @Bean
    public JmsListenerContainerFactory<?> topicListenerFactory(ConnectionFactory connectionFactory,
                                                    DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        // This provides all boot's default to this factory, including the message converter
        
        configurer.configure(factory, connectionFactory);
        factory.setErrorHandler(t -> System.err.println("An error has occurred in the message" + t.getMessage()));
        //factory.setMessageConverter(jacksonJmsMessageConverter());
        factory.setPubSubDomain(true);
        factory.setSubscriptionDurable(true);
        factory.setClientId(SUBSCRIPTION_ID);
        // You could still override some of Boot's default if necessary.
        return factory;
    }

}