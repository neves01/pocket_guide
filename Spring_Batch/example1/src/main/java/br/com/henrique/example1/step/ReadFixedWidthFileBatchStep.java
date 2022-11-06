package br.com.henrique.example1.step;

import br.com.henrique.example1.model.Client;
import br.com.henrique.example1.reader.TransactionReader;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


@Configuration
public class ReadFixedWidthFileBatchStep {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

//    @Bean
//    public Step readFixedStep(ItemReader<Client> differentMethodReader, ItemWriter<Client> fixedMethodWriter) {
//        return stepBuilderFactory
//                .get("readFixedStep")
//                .<Client, Client>chunk(1)
//                .reader(differentMethodReader)
//                .writer(fixedMethodWriter)
//                .build();
//    }


    @Bean
    public Step readDiffStep(
            MultiResourceItemReader<Client> multiResourceItemReader,
            ItemWriter genericWriter) {
        return stepBuilderFactory
                .get("readDiffStep")
                .chunk(1)
                .reader(multiResourceItemReader)
                .writer(genericWriter)
                .build();
    }

}
