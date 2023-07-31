package org.engina;

import org.engina.dto.request.ScoreUpdateRequestDto;
import org.engina.repository.entity.Score;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IScoreMapper {
    IScoreMapper INSTANCE = Mappers.getMapper(IScoreMapper.class);

    Score toScore(final ScoreUpdateRequestDto dto);
}
