package ee.leima.matis.backend.business.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ee.leima.matis.backend.business.dto.FilterDTO;
import ee.leima.matis.backend.business.dto.WorksLiteDTO;
import ee.leima.matis.backend.domain.entity.Work;
import ee.leima.matis.backend.domain.service.WorkService;
import jakarta.annotation.Resource;

@Service
public class WorksService {

    @Resource
    private WorkService workService;
    
    @Resource
    private WorkMapper workMapper;

    public List<WorksLiteDTO> getWorks(FilterDTO filter) {
        List<Work> works = workService.getWorks(filter); // Fetch works
        return workMapper.toWorksLiteDTOs(works); // Map to DTO list
    }
    
