package com.master.demo.Kafka;

import com.master.demo.Entities.Partida;
import com.master.demo.Entities.PartidaKafka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private static final String TOPIC= "my_topic";

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void writeMessage(PartidaKafka partida){
        System.out.println(partida.getIdPartida());
        this.kafkaTemplate.send(TOPIC, partida);

    }

}
