//package br.com.henrique.example1.step;
//
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@EnableBatchProcessing
//public class PrintHelloBatchStep {
//
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;
//
//    @Bean
//    public Step printHelloStep(Tasklet printHelloTasklet) {
//        return stepBuilderFactory
//                .get("printHelloStep")
//                .tasklet(printHelloTasklet)
//                .build();
//    }
//}
