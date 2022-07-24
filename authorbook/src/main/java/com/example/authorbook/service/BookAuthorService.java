package com.example.authorbook.service;

import com.example.authorbook.domain.CommonResponse;
import com.example.authorbook.domain.dto.BookAuthorDto;

public interface BookAuthorService {

    CommonResponse getAllBookAuthor();
    CommonResponse getBookAuthorById(Long id);
    CommonResponse createBookAuthor(BookAuthorDto bookAuthorDto);
    CommonResponse updateBookAuthor(Long id, BookAuthorDto bookAuthorDto);
    CommonResponse deleteBookAuthor(Long id);
}
