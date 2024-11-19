package ee.leima.matis.backend.business.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class WorksController {

    @GetMapping("/get-works")
    @Operation(
        summary = "This returns list of works")
    public String getWorks() {
        return "This returns list of works";
    }

    @PostMapping("/add-works")
    @Operation(
        summary = "This adds a new entry to the list of works")
    public String addWorks() {
        return "This adds a new entry to the list of works";
    }
}
