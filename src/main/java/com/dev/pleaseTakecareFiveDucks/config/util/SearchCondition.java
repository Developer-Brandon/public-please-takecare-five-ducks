package com.dev.pleaseTakecareFiveDucks.config.util;

import lombok.ToString;
import org.springframework.web.util.UriComponentsBuilder;

import static java.lang.Math.max;
import static java.lang.Math.min;

@ToString
public class SearchCondition {


    public static final int DEFAULT_PAGE_SIZE = 10;
    public static final int MIN_PAGE_SIZE = 5;
    public static final int MAX_PAGE_SIZE = 50;

    // 현재 페이지
    private Integer currentPage = 1;

    // 하나의 네비게이션에서 보여주고자 하는 페이지의 사이즈
    private Integer pageSize = DEFAULT_PAGE_SIZE;

    // 검색 페이지네이션
    private String title = "";

    public SearchCondition() {
    }

    public SearchCondition(Integer currentPage, Integer pageSize) {

       this.currentPage = currentPage;
       this.pageSize = pageSize;
    }

    public SearchCondition(Integer currentPage,  Integer pageSize, String title) {

        this(currentPage, pageSize);
        this.title = title;
    }

    public String getQueryString() {
        return getQueryString(currentPage);
    }

    public String getQueryString(Integer currentPage) {

        // ?currentPage=10&pageSize=10&option=A&keyword=title
        return UriComponentsBuilder.newInstance()
                .queryParam("currentPage", currentPage)
                .queryParam("title", title)
                .build().toString();
    }

    public Integer getPage() {
        return currentPage;
    }

    public void setPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;

        // MIN_PAGE_SIZE <= pageSize <= MAX_PAGE_SIZE
        this.pageSize = max(MIN_PAGE_SIZE, min(this.pageSize, MAX_PAGE_SIZE));
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String keyword) {
        this.title = title;
    }

    public Integer getOffset() {
        return (currentPage - 1) * pageSize;
    }
}

