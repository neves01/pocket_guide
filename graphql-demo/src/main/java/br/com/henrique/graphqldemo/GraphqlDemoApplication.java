package br.com.henrique.graphqldemo;

import br.com.henrique.graphqldemo.model.Author;
import br.com.henrique.graphqldemo.model.Book;
import br.com.henrique.graphqldemo.repository.AuthorRepository;
import br.com.henrique.graphqldemo.repository.BookRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class GraphqlDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraphqlDemoApplication.class, args);
	}

	@Bean
	ApplicationRunner applicationRunner (AuthorRepository authorRepository, BookRepository bookRepository) {
		return args -> {
			var anAuthor1 = authorRepository.save(new Author("Joe"));
			var anAuthor2 = authorRepository.save(new Author("Doe"));

			bookRepository.saveAll(List.of(
					new Book("book1", "publisher1", anAuthor1),
					new Book("book2", "publisher2", anAuthor1),
					new Book("book3", "publisher3", anAuthor2),
					new Book("book4", "publisher4", anAuthor2)
			));
		};
	}

}
