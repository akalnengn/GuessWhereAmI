package org.engina.rabbitmq.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveGuessModel implements Serializable {
    Long cityid;
    String cityName;
    String cityArea;
    String hint;
    String photoWithBlur;
    String photoWithoutBlur;
}
