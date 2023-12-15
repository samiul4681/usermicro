package com.example.test.demo.service;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaListenerService {

    @KafkaListener(topics = "my-topic", groupId = "my-group-id")
    public void listenGroupMsg(String message) {
        System.out.println("Received Message in group: " + message);
    }

}
