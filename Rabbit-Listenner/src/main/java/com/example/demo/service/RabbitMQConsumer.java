package com.example.demo.service;

import com.example.demo.model.reversar.ReversaPago;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class RabbitMQConsumer {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQConsumer.class);

    @Value("${sample.rabbitmq.exchange}")
    private String exchange;

    @Value("${sample.rabbitmq.routingkey}")
    private String routingkey;

    @Value("${service.protocol}")
    private String protocolo;

    @Value("${service.url}")
    private String url;

    @Value("${service.path}")
    private String path;

    @Value("${listener.retry.max-interval}")
    private int intervalo;

    @Autowired
    private AmqpTemplate rabbitTemplate;

    //@RabbitListener(queues = "${sample.rabbitmq.queue}")
    public void recibeMensaje(Message message) {
        HttpHeaders header = getHeaders(message.getMessageProperties().getHeaders());
        ReversaPago pago = getPago(message.getBody());
        String serviceUrl = protocolo + "://" + url + path;
            try{
                Thread.sleep(intervalo*1000);
                RestTemplate restTemplate = new RestTemplate();
                HttpEntity<ReversaPago> entity = new HttpEntity<>(pago, header );
                ResponseEntity<String> response = restTemplate.postForEntity(serviceUrl, entity, String.class);
                logger.info("Pago aplicado con exito");
            }catch (HttpClientErrorException e){
                logger.info(e.getStatusCode() + " intentanto...");
            }catch(Exception e){
                logger.info(e.getMessage());
                logger.info("No response el servicio. ");
            }

        logger.info("No se pudo procesar el pago, volver a intentar mas tarde");
    }

    private HttpHeaders getHeaders(Map<String,Object> headers){
        HttpHeaders header = new HttpHeaders();
        headers.forEach((k,v) -> {
            header.set(k,v.toString());
        });
        return header;
    }

    private ReversaPago getPago(byte[] body){
        Gson gson = new Gson();
        return gson.fromJson(new String(body),ReversaPago.class);
    }

}