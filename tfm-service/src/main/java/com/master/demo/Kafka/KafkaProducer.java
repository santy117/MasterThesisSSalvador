package com.master.demo.Kafka;

import com.master.demo.Entities.LecturaPartida;
import com.master.demo.Entities.Notificacion;
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
    private static final String TOPIC_NOTIFICACIONES= "my_topic2";
    private static final String TOPIC_LECTURA_PARTIDAS= "my_topic4";
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void writeMessage(PartidaKafka partida){
        System.out.println(partida.getIdVersion());
        this.kafkaTemplate.send(TOPIC, partida);

    }

    public void insertPartidas(List<PartidaKafka> partidas){
        partidas.forEach(partida -> this.kafkaTemplate.send(TOPIC, partida));
    }
    public void insertNotificacion(Notificacion notificacion){
        System.out.println("Insertando notificacion: "+ notificacion.getMensaje());
        this.kafkaTemplate.send(TOPIC_NOTIFICACIONES, notificacion);
    }

    public void lecturaPartida(LecturaPartida lecturaPartida){
        System.out.println("Leyendo partidas de version: "+ lecturaPartida.getIdVersion());
        this.kafkaTemplate.send(TOPIC_LECTURA_PARTIDAS, lecturaPartida);
    }

}
