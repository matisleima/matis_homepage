package ee.leima.matis.backend.domain.entity;

import lombok.Data;

@Data
public class Product {

    private String name;
    private String type;
    private String unit;
    private String description;
    private String price;
    private Boolean inStock;
    private String tags;
}
