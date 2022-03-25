package com.master.demo.Kafka;

import com.master.demo.Entities.Partida;
import com.master.demo.Entities.PartidaKafka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KafkaProducer {

    private static final String TOPIC= "my_topic";

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void writeMessage(PartidaKafka partida){
        System.out.println(partida.getIdVersion());
        this.kafkaTemplate.send(TOPIC, partida);

    }

    public void insertPartidas(List<PartidaKafka> partidas){
        partidas.forEach(partida -> this.kafkaTemplate.send(TOPIC, partida));
    }

}
