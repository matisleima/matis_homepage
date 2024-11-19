package ee.leima.matis.backend.business.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class BioController {

    @GetMapping("/get-bio")
        @Operation(
            summary = "This returns biography")
    public String getBio() {
        return "This returns biography";
    }
}
