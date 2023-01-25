package com.dev.pleaseTakecareFiveDucks.book.domain.vo.result;

import com.dev.pleaseTakecareFiveDucks.anime.util.FinalizedYnEnum;
import com.dev.pleaseTakecareFiveDucks.book.domain.vo.BookTypeVO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Builder
@Getter
@Setter
public class RawBookTypeResultVO {

    ArrayList<BookTypeVO> bookTypeVOArrayList;
}
