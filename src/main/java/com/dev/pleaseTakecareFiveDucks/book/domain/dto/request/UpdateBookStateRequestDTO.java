package com.dev.pleaseTakecareFiveDucks.book.domain.dto.request;

import com.dev.pleaseTakecareFiveDucks.book.util.BookUseYnEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UpdateBookStateRequestDTO {
    Integer bookNo;
    BookUseYnEnum bookUseYnEnum;
}
