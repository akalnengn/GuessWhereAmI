package org.engina.mapper;

import org.engina.dto.request.CityRequestDto;
import org.engina.repository.entity.City;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ICityMapper {
    ICityMapper INSTANCE = Mappers.getMapper(ICityMapper.class);

    City toCity(final CityRequestDto dto);
}
