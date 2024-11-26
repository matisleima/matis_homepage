package ee.leima.matis.backend.business.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ee.leima.matis.backend.business.dto.ProductFilterDTO;
import ee.leima.matis.backend.business.dto.ProductResponse;
import ee.leima.matis.backend.business.service.ProductsService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;

@RestController
public class ProductController {

    @Resource
    private ProductsService productsService;

    @GetMapping("/get-products")
    @Operation(summary = "This returns a list of products optionally filtered by parameters")
    public ProductResponse getProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String unit,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String price,
            @RequestParam(required = false) Boolean inStock,
            @RequestParam(required = false) String tags) {

        // Compile query parameters into ProductFilterDTO
        ProductFilterDTO filter = new ProductFilterDTO();
        filter.setName(name);
        filter.setType(type);
        filter.setUnit(unit);
        filter.setDescription(description);
        filter.setPrice(price);
        filter.setInStock(inStock);
        filter.setTags(tags);

        // Return the wrapped response with count and product list
        return productsService.getProducts(filter);
    }

    @GetMapping("/get-products-general")
    @Operation(summary = "This returns a list of products filtered by a general search term")
    public ProductResponse getProductsGeneralSearch(@RequestParam(required = false) String generalTerm) {
        // Return the wrapped response with count and product list
        return productsService.getProducts(generalTerm);
    }
}
