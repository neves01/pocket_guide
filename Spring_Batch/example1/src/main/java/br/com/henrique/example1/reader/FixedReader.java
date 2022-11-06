package br.com.henrique.example1.reader;

import br.com.henrique.example1.model.Client;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class FixedReader {

    @StepScope
    @Bean
    public FlatFileItemReader<Client> fixedMethodReader(
            @Value("#{jobParameters['clientFile']}") Resource clientFile) {
        return new FlatFileItemReaderBuilder<Client>()
                .name("fixedMethodReaderX")
                .resource(clientFile)
                .fixedLength()
                .columns(new Range[]{new Range(1, 10), new Range(11, 20), new Range(21, 23), new Range(24, 43)})
                .names(new String[] {"name", "lastname", "age", "email"})
                .targetType(Client.class)
                .build();
    }

}
