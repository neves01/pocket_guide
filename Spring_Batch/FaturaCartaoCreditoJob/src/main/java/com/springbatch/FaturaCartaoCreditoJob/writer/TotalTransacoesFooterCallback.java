package com.springbatch.FaturaCartaoCreditoJob.writer;

import com.springbatch.FaturaCartaoCreditoJob.domain.FaturaCartaoCredito;
import org.springframework.batch.core.annotation.AfterChunk;
import org.springframework.batch.core.annotation.BeforeWrite;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.file.FlatFileFooterCallback;

import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;

public class TotalTransacoesFooterCallback implements FlatFileFooterCallback {

    private BigDecimal total = BigDecimal.ZERO;

    @Override
    public void writeFooter(Writer writer) throws IOException {
        writer.write(String.format("\n121s", "Total: " + NumberFormat.getCurrencyInstance().format(total)));
    }

    @BeforeWrite
    public void beforeWrite(List<FaturaCartaoCredito> faturas) {
        for (FaturaCartaoCredito faturaCartaoCredito : faturas) {
            total = total.add(faturaCartaoCredito.getTotal());
        }
    }

    @AfterChunk
    public void afterChunk(ChunkContext chunkContext) {
        total = BigDecimal.ZERO;
    }
}