package com.example.authorbook.service;

import com.example.authorbook.domain.CommonResponse;
import com.example.authorbook.domain.dto.AuthorPostDto;

public interface AuthorService {

    CommonResponse getAllAuthors();
    CommonResponse getAuthorById(Long id);
    CommonResponse createAuthor(AuthorPostDto authorPostDto);
    CommonResponse updateAuthor(Long id, AuthorPostDto authorPostDto);
    CommonResponse deleteAuthor(Long id);
}
