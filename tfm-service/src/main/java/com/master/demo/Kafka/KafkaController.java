package com.master.demo.Kafka;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

    private final KafkaProducer producer;

    @PostMapping("/sendMessage")
    public void writeMessageToTopic(@RequestParam("message") String message){
        this.producer.writeMessage(message);
    }

    public KafkaController(KafkaProducer producer) {
        this.producer = producer;
    }
}
