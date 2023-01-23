package com.dev.pleaseTakecareFiveDucks.config.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.util.UriComponentsBuilder;

import static java.lang.Math.min;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SearchCondition {

    public static final int DEFAULT_PAGE_SIZE = 10;
    public static final int MIN_PAGE_SIZE = 5;
    public static final int MAX_PAGE_SIZE = 50;

    // 현재 페이지
    private Integer currentPage = 1;

    // 하나의 네비게이션에서 보여주고자 하는 페이지의 사이즈
    private Integer pageSize = DEFAULT_PAGE_SIZE;

    // 검색 옵션
    private String option = "";

    // 검색 페이지네이션
    private String title = "";

    public SearchCondition(Integer currentPage, Integer pageSize) {

        this(currentPage, pageSize, "", "");
    }

    public SearchCondition(Integer currentPage, Integer pageSize, String option, String title) {

        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.option = option;
        this.title = title;
    }

    public String getQueryString() {
        return getQueryString(currentPage);
    }

    public String getQueryString(Integer currentPage) {

        // ?currentPage=10&pageSize=10&option=A&title=title
        return UriComponentsBuilder.newInstance()
                .queryParam("currentPage", currentPage)
                // .queryParam("pageSize", pageSize)
                // .queryParam("option", option)
                .queryParam("title", title)
                .build()
                .toString();
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;

        // MIN_PAGE_SIZE <= pageSize <= MAX_PAGE_SIZE
        this.pageSize = Math.max(MIN_PAGE_SIZE, min(this.pageSize, MAX_PAGE_SIZE));
    }

    public Integer getOffset() {
        return (currentPage - 1) * pageSize;
    }
}
