package ee.leima.matis.backend.business.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ee.leima.matis.backend.business.dto.ProductDTO;
import ee.leima.matis.backend.business.dto.ProductFilterDTO;
import ee.leima.matis.backend.business.dto.ProductResponse;
import ee.leima.matis.backend.domain.entity.Product;
import ee.leima.matis.backend.domain.service.ProductService;
import jakarta.annotation.Resource;

@Service
public class ProductsService {

    @Resource
    private ProductService productService;

    @Resource
    private GeneralMapper generalMapper;

    public ProductResponse getProducts(ProductFilterDTO filter) {
        List<Product> products = productService.getProducts(filter); // Fetch products based on filter
        List<ProductDTO> productDTOs = generalMapper.toProductDTOs(products); // Map to DTO list
        return new ProductResponse(productDTOs.size(), productDTOs); // Wrap response with count
    }

    public ProductResponse getProducts(String generalTerm) {
        List<Product> products = productService.getProducts(generalTerm); // Fetch products based on general search term
        List<ProductDTO> productDTOs = generalMapper.toProductDTOs(products); // Map to DTO list
        return new ProductResponse(productDTOs.size(), productDTOs); // Wrap response with count
    }
}
