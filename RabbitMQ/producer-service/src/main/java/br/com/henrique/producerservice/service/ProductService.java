package br.com.henrique.producerservice.service;

import dtos.ProductDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static constants.RabbitMQConstants.EXCG_NAME_MARKETPLACE;
import static constants.RabbitMQConstants.RK_PRODUCT_LOG;

@Log4j2
@Service
public class ProductService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void produce(ProductDTO productDTO) {
        log.info("Sent message " + productDTO.toString());
        rabbitTemplate.convertAndSend(EXCG_NAME_MARKETPLACE, RK_PRODUCT_LOG, productDTO);
    }
}
