package com.springbatch.migracaodados.writer;

import com.springbatch.migracaodados.domain.Pessoa;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.batch.item.support.builder.ClassifierCompositeItemWriterBuilder;
import org.springframework.classify.Classifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PessoaClassifierCompositeItemWriterConfig {

    @Bean
    public ClassifierCompositeItemWriter<Pessoa> pessoaClassifierCompositeItemWriter(
            JdbcBatchItemWriter<Pessoa> pessoaWriter,
            FlatFileItemWriter<Pessoa> pessoasInvalidasWriter
    ) {
        return new ClassifierCompositeItemWriterBuilder<Pessoa>()
                .classifier(classifier(pessoaWriter, pessoasInvalidasWriter))
                .build();
    }

    private Classifier<Pessoa, ItemWriter<? super Pessoa>> classifier(JdbcBatchItemWriter<Pessoa> pessoaWriter, FlatFileItemWriter<Pessoa> pessoasInvalidasWriter) {
        return new Classifier<Pessoa, ItemWriter<? super Pessoa>>() {
            @Override
            public ItemWriter<? super Pessoa> classify(Pessoa pessoa) {
                if (pessoa.isValid())
                    return pessoaWriter;
                else
                    return pessoasInvalidasWriter;
            }
        };
    }

}
