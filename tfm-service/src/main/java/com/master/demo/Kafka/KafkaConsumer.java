package com.master.demo.Kafka;

import com.master.demo.Entities.PartidaKafka;
import com.master.demo.service.PartidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer{

    private final PartidaService partidaService;

    @Autowired
    public KafkaConsumer(final PartidaService partidaService){
        this.partidaService = partidaService;
    }

    @KafkaListener(topics="my_topic", groupId="my_group_id")
    public void getMessage(PartidaKafka partida){
        System.out.println("Message received: " + partida);
        this.partidaService.insertarPartida(partida.getIdPartida(),partida.getGastos(), partida.getInformacion());
    }
}
