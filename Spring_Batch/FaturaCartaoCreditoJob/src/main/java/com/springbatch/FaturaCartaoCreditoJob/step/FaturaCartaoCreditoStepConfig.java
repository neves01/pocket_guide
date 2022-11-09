package com.springbatch.FaturaCartaoCreditoJob.step;

import com.springbatch.FaturaCartaoCreditoJob.domain.FaturaCartaoCredito;
import com.springbatch.FaturaCartaoCreditoJob.domain.Transacao;
import com.springbatch.FaturaCartaoCreditoJob.reader.FaturaCartaoCreditoReader;
import com.springbatch.FaturaCartaoCreditoJob.writer.TotalTransacoesFooterCallback;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FaturaCartaoCreditoStepConfig {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step faturaCartaoCreditoStep(
            ItemStreamReader<Transacao> lerTransacoesReader,
            ItemProcessor<FaturaCartaoCredito, FaturaCartaoCredito> carregarDadosClienteProcessor,
            ItemWriter<FaturaCartaoCredito> escreverFaturaCartaoCredito,
            TotalTransacoesFooterCallback totalTransacoesFooterCallback
    ) {
        return stepBuilderFactory
                .get("faturaCartaoCreditoStep")
                .<FaturaCartaoCredito, FaturaCartaoCredito>chunk(1)
                .reader(new FaturaCartaoCreditoReader(lerTransacoesReader))
                .processor(carregarDadosClienteProcessor)
                .writer(escreverFaturaCartaoCredito)
                .listener(totalTransacoesFooterCallback)
                .build();
    }
}
