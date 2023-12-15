package com.brainstation.usermicro.service;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaListenerService {

    @KafkaListener(topics = "user-events", groupId = "group-id")
    public void listenGroupMsg(String message) {

        System.out.println("Received Message in group: " + message);
    }

}
