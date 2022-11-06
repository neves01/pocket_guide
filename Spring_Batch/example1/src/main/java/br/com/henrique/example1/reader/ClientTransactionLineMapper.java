package br.com.henrique.example1.reader;

import br.com.henrique.example1.model.Client;
import br.com.henrique.example1.model.Transaction;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.mapping.PatternMatchingCompositeLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ClientTransactionLineMapper {

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Bean
    public PatternMatchingCompositeLineMapper lineMapper() {
        PatternMatchingCompositeLineMapper lineMapper = new PatternMatchingCompositeLineMapper<>();
        lineMapper.setTokenizers(tokenizers());
        lineMapper.setFieldSetMappers(fieldSetMappers());
        return lineMapper;
    }

    @SuppressWarnings("rawtypes")
    private Map<String, FieldSetMapper> fieldSetMappers() {
        Map<String, FieldSetMapper> fieldSetMappers = new HashMap<>();
        fieldSetMappers.put("0*", fieldSetMapper(Client.class));
        fieldSetMappers.put("1*", fieldSetMapper(Transaction.class));
        return fieldSetMappers;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private FieldSetMapper fieldSetMapper(Class aClass) {
        BeanWrapperFieldSetMapper fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(aClass);
        return fieldSetMapper;
    }

    private Map<String, LineTokenizer> tokenizers() {
        Map<String, LineTokenizer> tokenizers = new HashMap<>();
        tokenizers.put("0*", clientLineTokenizer());
        tokenizers.put("1*", transactionLineTokenizer());
        return tokenizers;
    }

    private LineTokenizer clientLineTokenizer() {
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames("name", "lastname", "age", "email");
        lineTokenizer.setIncludedFields(1, 2, 3, 4);
        return lineTokenizer;
    }

    private LineTokenizer transactionLineTokenizer() {
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames("id", "description", "value");
        lineTokenizer.setIncludedFields(1, 2, 3);
        return lineTokenizer;
    }


}
