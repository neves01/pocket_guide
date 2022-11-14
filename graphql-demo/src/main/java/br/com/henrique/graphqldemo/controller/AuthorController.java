package br.com.henrique.graphqldemo.controller;

import br.com.henrique.graphqldemo.model.Author;
import br.com.henrique.graphqldemo.model.Book;
import br.com.henrique.graphqldemo.repository.AuthorRepository;
import br.com.henrique.graphqldemo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @QueryMapping
    private Iterable<Author> authors() {
        return authorRepository.findAll();
    }

    @QueryMapping
    private Optional<Author> authorById(@Argument Long id) {
        return authorRepository.findById(id);
    }

    @MutationMapping
    private Book addBook(@Argument BookInput book){
        Author anAuthor = authorRepository.findById(book.authorId()).orElseThrow(() -> new IllegalArgumentException("author not found"));
        Book aBook = new Book(book.title(), book.publisher(), anAuthor);
        return bookRepository.save(aBook);
    }

    record BookInput(String title, String publisher, Long authorId) {}
}
