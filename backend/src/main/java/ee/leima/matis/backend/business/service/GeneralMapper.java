package ee.leima.matis.backend.business.service;


import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import ee.leima.matis.backend.business.dto.ProductDTO;
import ee.leima.matis.backend.business.dto.WorksDTO;
import ee.leima.matis.backend.domain.entity.Product;
import ee.leima.matis.backend.domain.entity.Work;

@Mapper(componentModel = "spring")
public interface GeneralMapper {

    GeneralMapper INSTANCE = Mappers.getMapper(GeneralMapper.class);

    WorksDTO toWorksDTO(Work work);

    List<WorksDTO> toWorksDTOs(List<Work> works);

    ProductDTO toProductDTO(Product product);

    List<ProductDTO> toProductDTOs(List<Product> product);
}
