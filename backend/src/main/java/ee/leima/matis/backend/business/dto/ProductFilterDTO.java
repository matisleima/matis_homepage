package ee.leima.matis.backend.business.dto;

import lombok.Data;

@Data
public class ProductFilterDTO {

    private String name;
    private String type;
    private String unit;
    private String description;
    private String price;
    private Boolean inStock;
    private String tags;
}
