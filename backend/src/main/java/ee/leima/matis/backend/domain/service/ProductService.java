package ee.leima.matis.backend.domain.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ee.leima.matis.backend.business.dto.ProductFilterDTO;
import ee.leima.matis.backend.domain.entity.Product;
import ee.leima.matis.backend.domain.repository.ProductRepository;
import jakarta.annotation.Resource;

@Service
public class ProductService {

    @Resource
    private ProductRepository productRepository;

    public List<Product> getProducts(ProductFilterDTO filter) {
        try {
            List<Product> allProducts = productRepository.fetchProductData();
            return filterProducts(filter, null, allProducts);
        } catch (IOException e) {
            throw new RuntimeException("Failed to fetch product data from Google Spreadsheets.", e);
        }
    }

    public List<Product> getProducts(String generalTerm) {
        try {
            List<Product> allProducts = productRepository.fetchProductData();
            return filterProducts(null, generalTerm, allProducts);
        } catch (IOException e) {
            throw new RuntimeException("Failed to fetch product data from Google Spreadsheets.", e);
        }
    }

    private List<Product> filterProducts(ProductFilterDTO filter, String generalTerm, List<Product> allProducts) {
        return allProducts.stream()
                .filter(product -> (filter == null || matchesFilter(product, filter)) &&
                        (generalTerm == null || matchesGeneralTerm(product, generalTerm)))
                .collect(Collectors.toList());
    }

    private boolean matchesFilter(Product product, ProductFilterDTO filter) {
        return matchesString(product.getName(), filter.getName()) &&
                matchesString(product.getType(), filter.getType()) &&
                matchesString(product.getUnit(), filter.getUnit()) &&
                matchesString(product.getDescription(), filter.getDescription()) &&
                matchesString(product.getPrice(), filter.getPrice()) && // Adjusted for price as String
                matchesExact(product.getInStock(), filter.getInStock()) &&
                matchesString(product.getTags(), filter.getTags());
    }

    private boolean matchesGeneralTerm(Product product, String generalTerm) {
        String lowerTerm = generalTerm.toLowerCase();

        return matchesString(product.getName(), lowerTerm) ||
                matchesString(product.getType(), lowerTerm) ||
                matchesString(product.getUnit(), lowerTerm) ||
                matchesString(product.getDescription(), lowerTerm) ||
                matchesString(product.getPrice(), lowerTerm) || // Adjusted for price as String
                matchesString(product.getTags(), lowerTerm);
    }

    private boolean matchesString(String productValue, String filterValue) {
        return filterValue == null || filterValue.isEmpty() ||
                (productValue != null && productValue.toLowerCase().contains(filterValue.toLowerCase()));
    }

    private boolean matchesExact(Object productValue, Object filterValue) {
        return filterValue == null || productValue.equals(filterValue);
    }
}
