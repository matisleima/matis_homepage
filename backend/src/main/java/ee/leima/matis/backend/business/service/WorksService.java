package ee.leima.matis.backend.business.service;

import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

@Service
public class WorksService {

    @Resource
    private WorksService worksService;
    
    @Resource
    private WorkMapper workMapper;
}
