package ee.leima.matis.backend.business.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ee.leima.matis.backend.business.dto.FilterDTO;
import ee.leima.matis.backend.business.dto.WorksLiteDTO;
import ee.leima.matis.backend.business.service.WorksService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;

@RestController
public class WorksController {

    @Resource
    private WorksService worksService;

    @GetMapping("/get-works")
    @Operation(
        summary = "This returns list of works")
    public List<WorksLiteDTO> getWorks(@RequestParam FilterDTO filter) {
        return worksService.getWorks(filter);
    }

    @PostMapping("/add-works")
    @Operation(
        summary = "This adds a new entry to the list of works")
    public String addWorks() {
        return "This adds a new entry to the list of works";
    }
}
