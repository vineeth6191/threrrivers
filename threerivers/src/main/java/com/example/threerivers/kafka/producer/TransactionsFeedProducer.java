package com.example.threerivers.kafka.producer;

import com.example.threerivers.kafka.TransactionsFeed;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TransactionsFeedProducer {

    private static final String TOPIC = "transactions_topic";
    @Autowired
    private KafkaTemplate<String, TransactionsFeed> kafkaTemplate;

    public void sendMessage(TransactionsFeed message){

        this.kafkaTemplate.send(TOPIC,message);
    }

    @Bean
    public NewTopic createTopic(){

        return new NewTopic(TOPIC,3,(short) 1);
    }

}
