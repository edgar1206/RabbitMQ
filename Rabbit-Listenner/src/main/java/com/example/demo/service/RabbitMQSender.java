package com.example.demo.service;

import com.example.demo.model.reversar.ReversaPago;
import com.google.gson.Gson;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.logging.Logger;

@Service
public class RabbitMQSender {

    private static final Logger log = Logger.getLogger(RabbitMQSender.class.getName());

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${sample.rabbitmq.exchange}")
    private String exchange;

    @Value("${sample.rabbitmq.routingkey}")
    private String routingkey;


    public void send() {
        log.info("recibe pago a encolar en Rabbit "+exchange);
        Gson gson = new Gson();
        String json = gson.toJson("hola");
        Message message = MessageBuilder
                .withBody(json.getBytes())
                .build();
        rabbitTemplate.convertAndSend(exchange, routingkey, message);
    }

}