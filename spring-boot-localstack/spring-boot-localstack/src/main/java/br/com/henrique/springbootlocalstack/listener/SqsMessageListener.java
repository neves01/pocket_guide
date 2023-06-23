package br.com.henrique.springbootlocalstack.listener;

import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class SqsMessageListener {

    @SqsListener("${sqsQueueName}")
    public void queueListener(String message) {
        try {
            log.info(StandardCharsets.UTF_8.encode(message).toString());
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }

}