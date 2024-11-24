package ee.leima.matis.backend.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ee.leima.matis.backend.business.dto.FilterDTO;
import ee.leima.matis.backend.domain.entity.Work;
import ee.leima.matis.backend.domain.repository.WorkRepository;
import jakarta.annotation.Resource;

@Service
public class WorkService {

    @Resource
    private WorkRepository workRepository;

    List<Work> getWorks(FilterDTO filter) {
        workRepository.fetchWorkData();
        //filter works here
        List<Work> filteredWorks = filterWorks(filter);
    }
    
    List<Work> filterWorks(FilterDTO filter) {
        
    }
}
