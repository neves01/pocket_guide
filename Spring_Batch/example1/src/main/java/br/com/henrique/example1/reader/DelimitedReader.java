package br.com.henrique.example1.reader;

import br.com.henrique.example1.model.Client;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class DelimitedReader {

    @StepScope
    @Bean
    public FlatFileItemReader<Client> delimitedMethodReader(
            @Value("#{jobParameters['clientDelimitedFile']}") Resource clientFile) {
        return new FlatFileItemReaderBuilder<Client>()
                .name("delimitedMethodReader")
                .resource(clientFile)
                .delimited()
                .names(new String[] {"name", "lastname", "age", "email"})
                .targetType(Client.class)
                .build();
    }

}
