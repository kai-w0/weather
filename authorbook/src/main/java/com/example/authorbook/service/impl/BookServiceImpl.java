package com.example.authorbook.service.impl;

import com.example.authorbook.domain.Book;
import com.example.authorbook.domain.CommonResponse;
import com.example.authorbook.domain.dto.BookDto;
import com.example.authorbook.exception.ResourceNotFoundException;
import com.example.authorbook.repository.BookRepository;
import com.example.authorbook.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final Logger log = LoggerFactory.getLogger(AuthorServiceImpl.class);

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public CommonResponse getAllBooks() {
        CommonResponse response = new CommonResponse();
        List<BookDto> bookDtos = bookRepository.findAll().stream().map(b -> new BookDto(b)).collect(Collectors.toList());
        response.setData(bookDtos);
        return response;
    }

    @Override
    public CommonResponse getBookById(Long id) {
        CommonResponse response = new CommonResponse();
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("book not found"));
        if (book == null) {
            log.error("book not found");
        }
        response.setData(book);
        return response;
    }

    @Override
    public CommonResponse createBook(BookDto bookDto) {
        CommonResponse response = new CommonResponse();
        Book book = new Book();
        book.setId(bookDto.getId());
        book.setName(bookDto.getName());
        book.setBookAuthors(bookDto.getBookAuthors());
        bookRepository.save(book);
        response.setData("created successfully");
        return response;
    }

    @Override
    public CommonResponse updateBook(Long id, BookDto bookDto) {
        CommonResponse response = new CommonResponse();
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        if (book == null) {
            log.error("book not found");
        }
        book.setName(bookDto.getName());
        book.setBookAuthors(bookDto.getBookAuthors());
        bookRepository.save(book);
        response.setData("update successfully");
        return response;
    }

    @Override
    public CommonResponse deleteBook(Long id) {
        CommonResponse response = new CommonResponse();
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        if (book == null) {
            log.error("book not found");
        }
        bookRepository.delete(book);
        response.setData("deleted successfully");
        return response;
    }
}
