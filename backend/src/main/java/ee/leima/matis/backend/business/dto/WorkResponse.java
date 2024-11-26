package ee.leima.matis.backend.business.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class WorkResponse {
    private int count;
    private List<WorksDTO> works;
}