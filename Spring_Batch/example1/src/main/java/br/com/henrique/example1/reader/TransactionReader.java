package br.com.henrique.example1.reader;

import br.com.henrique.example1.model.Client;
import br.com.henrique.example1.model.Transaction;
import org.springframework.batch.item.*;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.ResourceAwareItemReaderItemStream;
import org.springframework.core.io.Resource;

public class TransactionReader implements ItemStreamReader<Client>, ResourceAwareItemReaderItemStream<Client> {

    private Object actualObject;
//    private ItemStreamReader<Object> delegate;
    private FlatFileItemReader<Object> delegate;

    public TransactionReader(FlatFileItemReader<Object> delegate) {
        this.delegate = delegate;
    }

    @Override
    public Client read() throws Exception {
        if (actualObject == null) {
            actualObject = delegate.read();
        }

        Client client = (Client) actualObject;
        actualObject = null;

        if (client != null) {
            while(peek() instanceof Transaction) {
                client.getTransactions().add((Transaction) actualObject);
            }
        }
        return client;
    }

    private Object peek() throws Exception {
        actualObject = delegate.read();
        return actualObject;
    }

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        delegate.open(executionContext);
    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
        delegate.update(executionContext);
    }

    @Override
    public void close() throws ItemStreamException {
        delegate.close();
    }

    @Override
    public void setResource(Resource resource) {
        delegate.setResource(resource);
    }
}
