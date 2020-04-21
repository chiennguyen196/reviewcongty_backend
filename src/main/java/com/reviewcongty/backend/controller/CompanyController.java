package com.reviewcongty.backend.controller;

import com.reviewcongty.backend.dao.entity.Company;
import com.reviewcongty.backend.service.CompanyService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping({"", "/", "/latest"})
    public ResponseEntity<Page<Company>> getLatestUpdated(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int pageSize) {
        return ResponseEntity.ok(companyService.getLatestUpdated(page, pageSize));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> findById(@PathVariable String id) {
        return ResponseEntity.ok(companyService.findById(id));
    }


}
