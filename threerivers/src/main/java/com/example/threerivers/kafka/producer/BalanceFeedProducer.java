package com.example.threerivers.kafka.producer;

import com.example.threerivers.kafka.BalanceFeed;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class BalanceFeedProducer {

    private static final String TOPIC = "balance_topic";
    @Autowired
    private KafkaTemplate<String, BalanceFeed> kafkaTemplate;

    public void sendMessage(BalanceFeed message){

        this.kafkaTemplate.send(TOPIC,message);
    }

    @Bean
    public NewTopic createTopic(){

        return new NewTopic(TOPIC,3,(short) 1);
    }

}
