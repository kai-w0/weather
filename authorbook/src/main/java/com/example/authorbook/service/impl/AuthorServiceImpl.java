package com.example.authorbook.service.impl;

import com.example.authorbook.domain.Author;
import com.example.authorbook.domain.CommonResponse;
import com.example.authorbook.domain.dto.AuthorPostDto;
import com.example.authorbook.exception.ResourceNotFoundException;
import com.example.authorbook.repository.AuthorRepository;
import com.example.authorbook.service.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final Logger log = LoggerFactory.getLogger(AuthorServiceImpl.class);

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public CommonResponse getAllAuthors() {
        CommonResponse response = new CommonResponse();
        List<AuthorPostDto> authorPostDtos = authorRepository.findAll().stream().map(a -> new AuthorPostDto(a)).collect(Collectors.toList());
        response.setData(authorPostDtos);
        return response;
    }

    @Override
    public CommonResponse getAuthorById(Long id) {
        CommonResponse response = new CommonResponse();
        Author author = authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author not found"));
        if (author == null) {
            log.error("author not found");
        }
        response.setData(new AuthorPostDto(author));
        return response;
    }

    @Override
    public CommonResponse createAuthor(AuthorPostDto authorPostDto) {
        CommonResponse response = new CommonResponse();
        Author author = new Author();
        author.setId(author.getId());
        author.setName(authorPostDto.getName());
        author.setBookAuthors(authorPostDto.getBookAuthors());
        authorRepository.save(author);
        response.setData("created successfully");
        return response;
    }

    @Override
    public CommonResponse updateAuthor(Long id, AuthorPostDto authorPostDto) {
        CommonResponse response = new CommonResponse();
        Author author = authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author not found"));
        if (author == null) {
            log.error("author not found");
        }
        author.setName(authorPostDto.getName());
        author.setBookAuthors(authorPostDto.getBookAuthors());
        authorRepository.save(author);
        response.setData("update successfully");
        return response;
    }

    @Override
    public CommonResponse deleteAuthor(Long id) {
        CommonResponse response = new CommonResponse();
        Author author = authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author not found"));
        if (author == null) {
            log.error("author not found");
        }
        authorRepository.delete(author);
        response.setData("deleted successfully");
        return response;
    }
}
