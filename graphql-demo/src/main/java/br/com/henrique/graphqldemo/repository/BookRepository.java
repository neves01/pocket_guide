package br.com.henrique.graphqldemo.repository;

import br.com.henrique.graphqldemo.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
}
