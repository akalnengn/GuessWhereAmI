package org.engina.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityRequestDto {
    String cityName;
    String cityArea;
    String hint;
    String photoWithBlur;
    String photoWithoutBlur;
}
