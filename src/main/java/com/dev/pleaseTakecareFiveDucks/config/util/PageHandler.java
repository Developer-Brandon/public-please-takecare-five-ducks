package com.dev.pleaseTakecareFiveDucks.config.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.UriComponentsBuilder;

public class PageHandler {

    private static final Logger logger = LoggerFactory.getLogger(PageHandler.class);

    private SearchCondition sc;

    // 하나의 네비게이션의 사이즈
    // client에서 요청하는 page size와 동일
    public final int PAGE_SIZE = 10;

    // 화면에 보여줄(Navigation의) 첫 페이지
    private int beginPage;

    // 화면에 보여줄(Navigation의) 마지막 페이지
    private int endPage;

    // 게시물의 총 갯수
    private int totalCnt;

    // 전체 페이지의 갯수
    private int totalPage;

    // 이후를 보여줄지의 여부. endPage==totalPage이면, showNext는 false
    private boolean showNext;

    // 이전을 보여줄지의 여부. beginPage==1이 아니면 showPrev는 false
    private boolean showPrev;

    public PageHandler(int totalCnt, int currentPage, String title) {
        this(totalCnt, new SearchCondition(currentPage, 10, title));
    }

    public PageHandler(int totalCnt, Integer currentPage) {

        this(totalCnt, new SearchCondition(currentPage, 10));
    }

    public PageHandler(int totalCnt, Integer currentPage, Integer pageSize) {

        // 총 게시물의 수, 현재 페이지, 페이지 사이즈
        this(totalCnt, new SearchCondition(currentPage, pageSize));
    }

    public PageHandler(int totalCnt, SearchCondition sc) {

        this.totalCnt = totalCnt;
        this.sc = sc;

        doPaging(totalCnt, sc);
    }

    private void doPaging(int totalCnt, SearchCondition sc) {

        // [총 페이지의 개수를 구하는 식]
        // 1. 전체 게시물의 개수를 하나의 네비게이션에서 보여주고자 하는 개수만큼 나눕니다.
        // 2. 전체 게시물의 개수에서 하나의 네비게이션에서 보여주고자 하는 개수만큼 나누었을때 나머지가 0이 아니라면 1, 맞다면 0으로 값을 바꾼 후에
        // 하나의 페이지를 더 더해줍니다(totalCnt % sc.getPageSize() == 0 ? 0 : 1)

        totalPage = totalCnt / sc.getPageSize() + (totalCnt % sc.getPageSize() == 0 ? 0 : 1);

        // [현재의 page가 totalPage보다 크지 않게 조정해줍니다]
        //
        sc.setPage(Math.min(sc.getPage(), totalPage));

        // [beginPage 구하는 식]
        // 현재의 페이지가 5면, beginPage는 1
        // 현재의 페이지가 11이면, beginPage는 11
        // 현재의 페이지가 23이면, beginPage는 21

        // 나누기 10을하고, 곱하기 10을 하면 1의자리수가 날아갑니다.
        beginPage = (sc.getPage() - 1) / PAGE_SIZE * PAGE_SIZE + 1;

        System.out.println("beginPage: " + beginPage);

        // [endPage를 구하는 식]
        // 가장 마지막 페이지를 구할 때에, 지금 현재 페이지의 beginPage에서 보여주고자 하는 navSize를 더해서 endPage를 구합니다.
        // 단, totalPage 보다 크면안되니까, 둘중 비교해서 작은값으로 endPage를 setting 해줍니다.
        endPage = Math.min(beginPage + PAGE_SIZE - 1, totalPage);

        System.out.println("endPage: " + endPage);

        // 만약 현재의 페이지가 1페이지가 아니면 앞으로 가는 버튼을 보여줍니다.
        showPrev = (beginPage != sc.getPage());

        // 만약 현재의 페이지가 마지막 페이지가 아니면 뒤로 가는 버튼을 보여줍니다.
        showNext = (endPage != sc.getPage());
    }

    public String getQueryString() {
        return getQueryString(this.sc.getPage());
    }

    public String getQueryString(Integer page) {

        // ?page=10&pageSize=10&option=A&keyword=title
        return UriComponentsBuilder.newInstance()
                .queryParam("page", page)
                .queryParam("title", sc.getTitle())
                .build().toString();
    }

    void print() {

        logger.info("page=", sc.getPage());

        logger.info(showPrev ? "PREV " : "");

        for (int i = beginPage; i <= endPage; i++) {
            logger.info(String.valueOf(i));
        }

        logger.info(showNext ? " NEXT" : "");
    }

    public SearchCondition getSc() {
        return sc;
    }

    public void setSc(SearchCondition sc) {
        this.sc = sc;
    }

    public int getTotalCnt() {
        return totalCnt;
    }

    public void setTotalCnt(int totalCnt) {
        this.totalCnt = totalCnt;
    }

    public int getBeginPage() {
        return beginPage;
    }

    public void setBeginPage(int beginPage) {
        this.beginPage = beginPage;
    }

    public int getPAGE_SIZE() {
        return PAGE_SIZE;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public void setShowPrev(Boolean showPrev) {
        this.showPrev = showPrev;
    }

    public boolean getShowPrev() {
        return showPrev;
    }

    public void setShowNext(Boolean showNext) {
        this.showNext = showNext;
    }

    public boolean getShowNext() {
        return showNext;
    }

    @Override
    public String toString() {
        return "PageHandler{" +
                "sc=" + sc +
                ", totalCnt=" + totalCnt +
                ", beginPage=" + beginPage +
                ", PAGE_SIZE=" + PAGE_SIZE +
                ", totalPage=" + totalPage +
                ", endPage=" + endPage +
                ", showPrev=" + showPrev +
                ", showNext=" + showNext +
                '}';
    }
}
