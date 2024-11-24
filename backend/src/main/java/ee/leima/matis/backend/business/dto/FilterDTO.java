package ee.leima.matis.backend.business.dto;

import lombok.Data;

@Data
public class FilterDTO {

    private String name;
    private String instruments;
    private int year;
}
