package com.bookmarker.api.service;

import com.bookmarker.api.domain.Bookmark;
import com.bookmarker.api.domain.BookmarkRepository;
import com.bookmarker.api.dto.BookmarkDTO;
import com.bookmarker.api.dto.BookmarkMapper;
import com.bookmarker.api.dto.BookmarksDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BookmarkService {
    private final BookmarkRepository repository;
    private final BookmarkMapper mapper;

    //Constructor Injection
//    public BookmarkService(BookmarkRepository repository) {
//        this.repository = repository;
//    }

    @Transactional(readOnly = true)
    public BookmarksDTO getBookmarks(Integer page) {
        //JPA 의 페이지 언어가 0부터 시작하기때문
        int pageNo = page < 1 ? 0 : page - 1;
        Pageable pageable = PageRequest.of(pageNo, 10, Sort.Direction.DESC, "id");
        //Page<Bookmark> => Page<BookMarkDto>
        //Page<BookmarkDTO> bookmarkPage = repository.findAll(pageable)
                //map(Function) Function의 추상메서드 R apply(T t)
                //.map(bookmark -> mapper.toDTO(bookmark));
                //Method Reference
                //.map(mapper::toDTO);
        Page<BookmarkDTO> bookmarkPage = repository.findBookmarks(pageable);
        return new BookmarksDTO(bookmarkPage);
    }
    
}