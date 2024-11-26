package ee.leima.matis.backend.business.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ee.leima.matis.backend.business.dto.WorkFilterDTO;
import ee.leima.matis.backend.business.dto.WorkResponse;
import ee.leima.matis.backend.business.dto.WorksDTO;
import ee.leima.matis.backend.domain.entity.Work;
import ee.leima.matis.backend.domain.service.WorkService;
import jakarta.annotation.Resource;

@Service
public class WorksService {

    @Resource
    private WorkService workService;

    @Resource
    private GeneralMapper workMapper;

    public WorkResponse getWorks(WorkFilterDTO filter) {
        List<Work> works = workService.getWorks(filter); // Fetch works based on the provided filter
        List<WorksDTO> worksDTOs = workMapper.toWorksDTOs(works); // Map the works to their DTO equivalents
        return new WorkResponse(worksDTOs.size(), worksDTOs); // Wrap the response with count and the works list
    }

    public WorkResponse getWorks(String generalTerm) {
        List<Work> works = workService.getWorks(generalTerm); // Fetch works based on a general search term
        List<WorksDTO> worksDTOs = workMapper.toWorksDTOs(works); // Map the works to their DTO equivalents
        return new WorkResponse(worksDTOs.size(), worksDTOs); // Wrap the response with count and the works list
    }
}
