package com.dev.pleaseTakecareFiveDucks.movie.domain.dto.request;

import com.dev.pleaseTakecareFiveDucks.movie.util.MovieUseYnEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UpdateMovieStateRequestDTO {
    Integer movieNo;
    MovieUseYnEnum movieUseYnEnum;
}
