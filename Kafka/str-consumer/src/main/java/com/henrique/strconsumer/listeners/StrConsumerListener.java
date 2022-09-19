package com.henrique.strconsumer.listeners;

import com.henrique.strconsumer.custom.StrConsumerCustomListener;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class StrConsumerListener {

    @SneakyThrows
    @StrConsumerCustomListener(groupId = "group-1")
    public void listener(String message) {
        log.info("CREATE :: message {} ", message);
        throw new IllegalArgumentException("EXCEPTION...");
    }

    @KafkaListener(groupId = "group-2", topics = "str-topic", containerFactory = "validMessageContainerFactory")
    public void log(String message) {
        log.info("LOG :: message {} ", message);
    }
}
