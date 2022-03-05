package com.master.demo.Kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private static final String TOPIC= "my_topic";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void writeMessage(String mesg){

        this.kafkaTemplate.send(TOPIC, mesg);
    }

}
