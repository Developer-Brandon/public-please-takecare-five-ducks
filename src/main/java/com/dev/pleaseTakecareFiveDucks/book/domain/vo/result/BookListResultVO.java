package com.dev.pleaseTakecareFiveDucks.book.domain.vo.result;

import com.dev.pleaseTakecareFiveDucks.book.domain.vo.BookVO;
import com.dev.pleaseTakecareFiveDucks.config.util.PageHandler;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookListResultVO {
    PageHandler pageHandler;
    List<BookVO> bookVOList;
}
