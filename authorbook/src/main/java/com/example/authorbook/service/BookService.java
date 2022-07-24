package com.example.authorbook.service;

import com.example.authorbook.domain.CommonResponse;
import com.example.authorbook.domain.dto.BookDto;

public interface BookService {

    CommonResponse getAllBooks();
    CommonResponse getBookById(Long id);
    CommonResponse createBook(BookDto bookDto);
    CommonResponse updateBook(Long id, BookDto bookDto);
    CommonResponse deleteBook(Long id);
}
