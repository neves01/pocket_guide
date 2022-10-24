package br.com.henrique.producerservice.service;

import br.com.henrique.producerservice.config.RabbitMQConfig;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class StringService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void produce(String message) {
        log.info("Sent message " + message);
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCG_NAME_MARKETPLACE, RabbitMQConfig.RK_PRODUCT_LOG, message);
    }
}
