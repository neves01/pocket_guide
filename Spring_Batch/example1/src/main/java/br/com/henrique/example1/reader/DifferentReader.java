package br.com.henrique.example1.reader;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;

@Configuration
public class DifferentReader {

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Bean
    @StepScope
    @Primary
    public FlatFileItemReader differentMethodReader(
            @Value("#{jobParameters['clientDifferentFile']}") Resource clientFile,
            LineMapper lineMapper) {
        return new FlatFileItemReaderBuilder()
                .name("differentMethodReader")
                .resource(clientFile)
                .lineMapper(lineMapper)
                .build();
    }

}
