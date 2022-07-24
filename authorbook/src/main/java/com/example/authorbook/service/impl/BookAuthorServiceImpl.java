package com.example.authorbook.service.impl;

import com.example.authorbook.domain.BookAuthor;
import com.example.authorbook.domain.CommonResponse;
import com.example.authorbook.domain.dto.BookAuthorDto;
import com.example.authorbook.exception.ResourceNotFoundException;
import com.example.authorbook.repository.BookAuthorRepository;
import com.example.authorbook.service.BookAuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookAuthorServiceImpl implements BookAuthorService {

    private final Logger log = LoggerFactory.getLogger(BookAuthorServiceImpl.class);

    private final BookAuthorRepository bookAuthorRepository;

    @Autowired
    public BookAuthorServiceImpl(BookAuthorRepository bookAuthorRepository) {
        this.bookAuthorRepository = bookAuthorRepository;
    }

    @Override
    public CommonResponse getAllBookAuthor() {
        CommonResponse response = new CommonResponse();
        List<BookAuthorDto> bookAuthorDtos = bookAuthorRepository.findAll().stream().map(ba -> new BookAuthorDto(ba)).collect(Collectors.toList());
        response.setData(bookAuthorDtos);
        return response;
    }

    @Override
    public CommonResponse getBookAuthorById(Long id) {
        CommonResponse response = new CommonResponse();
        BookAuthor bookAuthor = bookAuthorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("BookAuthor not found"));
        if (bookAuthor == null) {
            log.error("bookAuthor not found");
        }
        response.setData(bookAuthor);
        return response;
    }

    @Override
    public CommonResponse createBookAuthor(BookAuthorDto bookAuthorDto) {
        CommonResponse response = new CommonResponse();
        BookAuthor ba = new BookAuthor();
        ba.setId(bookAuthorDto.getId());
        ba.setBook(bookAuthorDto.getBook());
        ba.setAuthor(bookAuthorDto.getAuthor());
        bookAuthorRepository.save(ba);
        response.setData("created successfully");
        return response;
    }

    @Override
    public CommonResponse updateBookAuthor(Long id, BookAuthorDto bookAuthorDto) {
        CommonResponse response = new CommonResponse();
        BookAuthor ba = bookAuthorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("BookAuthor not found"));
        if (ba == null) {
            log.error("bookAuthor not found");
        }
        ba.setBook(bookAuthorDto.getBook());
        ba.setAuthor(bookAuthorDto.getAuthor());
        bookAuthorRepository.save(ba);
        response.setData("update successfully");
        return response;
    }

    @Override
    public CommonResponse deleteBookAuthor(Long id) {
        CommonResponse response = new CommonResponse();
        BookAuthor ba = bookAuthorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("BookAuthor not found"));
        if (ba == null) {
            log.error("bookAuthor not found");
        }
        bookAuthorRepository.delete(ba);
        response.setData("deleted successfully");
        return response;
    }
}
