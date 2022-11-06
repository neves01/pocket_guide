package br.com.henrique.example1.writer;

import br.com.henrique.example1.model.Client;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class FixedWriter {

//    @Bean
//    public ItemWriter<Client> fixedMethodWriter() {
//        return items -> items.forEach(System.out::println);
////        return items -> {
////            for (Client c : items) {
////                if (c.getName().equals("Maria"))
////                    throw new Exception();
////                else
////                    System.out.println(c);
////            }
////        };
//    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Bean
    public ItemWriter genericWriter() {
        return items -> items.forEach(System.out::println);
    }
}
