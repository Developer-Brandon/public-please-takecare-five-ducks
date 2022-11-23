package com.dev.pleaseTakecareFiveDucks.movie.domain.dto;

import com.dev.pleaseTakecareFiveDucks.movie.util.MovieUseYnEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UpdateMovieStateDTO {
    Integer movieNo;
    MovieUseYnEnum movieUseYnEnum;
}
