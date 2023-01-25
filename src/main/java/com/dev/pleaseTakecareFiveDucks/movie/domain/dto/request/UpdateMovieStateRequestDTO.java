package com.dev.pleaseTakecareFiveDucks.movie.domain.dto.request;

import com.dev.pleaseTakecareFiveDucks.movie.util.MovieUseYnEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMovieStateRequestDTO {
    Integer movieNo;
    MovieUseYnEnum movieUseYnEnum;
}
