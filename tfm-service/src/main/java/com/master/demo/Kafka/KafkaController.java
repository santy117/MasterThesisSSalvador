package com.master.demo.Kafka;

import com.master.demo.Entities.PartidaKafka;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

    private final KafkaProducer producer;

    @PostMapping(value = "/sendMessage",consumes = {"application/json"},produces = {"application/json"})
    public void writeMessageToTopic(@RequestBody PartidaKafka partida){
        this.producer.writeMessage(partida);
    }

    public KafkaController(KafkaProducer producer) {
        this.producer = producer;
    }
}
