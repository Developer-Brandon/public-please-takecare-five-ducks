package com.dev.pleaseTakecareFiveDucks.book.domain.dto.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DeleteBookInfoRequestDTO {
    Integer bookNo;
}
