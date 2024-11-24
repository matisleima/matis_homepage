package ee.leima.matis.backend.business.service;


import ee.leima.matis.backend.domain.entity.Work;
import ee.leima.matis.backend.business.dto.WorksLiteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WorkMapper {

    WorkMapper INSTANCE = Mappers.getMapper(WorkMapper.class);

    // Map a single Work entity to WorksLiteDTO
    @Mapping(source = "name", target = "name")
    @Mapping(source = "instruments", target = "instruments")
    @Mapping(source = "year", target = "year")
    WorksLiteDTO toWorksLiteDTO(Work work);

    // Map a list of Work entities to a list of WorksLiteDTOs
    List<WorksLiteDTO> toWorksLiteDTOs(List<Work> works);
}
