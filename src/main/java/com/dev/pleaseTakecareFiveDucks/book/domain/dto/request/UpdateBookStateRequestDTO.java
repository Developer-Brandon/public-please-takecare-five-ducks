package com.dev.pleaseTakecareFiveDucks.book.domain.dto.request;

import com.dev.pleaseTakecareFiveDucks.book.util.BookUseYnEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBookStateRequestDTO {
    Integer bookNo;
    BookUseYnEnum bookUseYnEnum;
}
