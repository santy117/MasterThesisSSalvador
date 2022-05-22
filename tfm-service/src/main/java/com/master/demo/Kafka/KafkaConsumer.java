package com.master.demo.Kafka;

import com.master.demo.Entities.MensajeIA;
import com.master.demo.Entities.Notificacion;
import com.master.demo.Entities.PartidaKafka;
import com.master.demo.service.NotificacionService;
import com.master.demo.service.PartidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer{

    private final PartidaService partidaService;
    private final NotificacionService notificacionService;

    private final KafkaProducer producer;

    @Autowired
    public KafkaConsumer(final PartidaService partidaService, KafkaProducer producer,final NotificacionService notificacionService){
        this.partidaService = partidaService;
        this.producer = producer;
        this.notificacionService = notificacionService;
    }

    @KafkaListener(topics="my_topic", groupId="my_group_id")
    public void getMessage(PartidaKafka partida){
        System.out.println("Message received: " + partida);
        this.partidaService.insertarPartida(partida.getIdVersion(),partida.getGastos(), partida.getInformacion());

    }

    @KafkaListener(topics="my_topic2", groupId="my_group_id")
    public void getNotificacion(Notificacion notificacion){
        System.out.println("Message received: " + notificacion.getMensaje());
        this.notificacionService.insertNotificacion(notificacion);
    }

    @KafkaListener(topics="my_topic3", groupId="my_group_id")
    public void getInfoIA(MensajeIA mensaje){
        System.out.println("Message received: "+mensaje.getUsuario()+" - "+ mensaje.getVersiones());
    }
}
