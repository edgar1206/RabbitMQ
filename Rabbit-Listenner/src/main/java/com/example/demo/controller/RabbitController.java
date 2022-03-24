package com.example.demo.controller;

import com.example.demo.service.RabbitMQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/rabbit")
public class RabbitController {

    @Autowired
    RabbitMQSender rabbitMQSender;

    @GetMapping(value = "/producer")
    public String producer() throws IOException {
        rabbitMQSender.send();
        return "Message sent to the RabbitMQ Successfully";
    }

    @PostMapping(value = "/map")
    public Map setVariable(@RequestBody Map map){
        System.out.println(map);
        return map;
    }


}
