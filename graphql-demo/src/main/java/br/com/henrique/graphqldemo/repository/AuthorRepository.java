package br.com.henrique.graphqldemo.repository;

import br.com.henrique.graphqldemo.model.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
