package com.reviewcongty.backend.controller;

import com.reviewcongty.backend.dao.entity.Company;
import com.reviewcongty.backend.dto.response.PageResponse;
import com.reviewcongty.backend.service.CompanyService;
import com.reviewcongty.backend.transform.Transformer;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService companyService;
    private final Transformer<PageResponse<Company>, Page<Company>> transformer;

    public CompanyController(CompanyService companyService, Transformer<PageResponse<Company>, Page<Company>> transformer) {
        this.companyService = companyService;
        this.transformer = transformer;
    }

    @GetMapping({"", "/", "/latest"})
    public ResponseEntity<PageResponse<Company>> getLatestUpdated(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int pageSize) {
        Page<Company> companies = companyService.getLatestUpdated(page, pageSize);
        return ResponseEntity.ok(transformer.transform(companies));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> findById(@PathVariable String id) {
        return ResponseEntity.ok(companyService.findById(id));
    }


}
