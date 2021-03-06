package com.reviewcongty.backend.api.controller;

import com.reviewcongty.backend.api.response.PageResponse;
import com.reviewcongty.backend.api.transform.Transformer;
import com.reviewcongty.backend.core.dao.entity.Company;
import com.reviewcongty.backend.core.dao.entity.SearchedCompany;
import com.reviewcongty.backend.core.service.CompanyService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/companies")
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
        Page<Company> companies = companyService.getLatestUpdated(page - 1, pageSize);
        return ResponseEntity.ok(transformer.transform(companies));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> findById(@PathVariable String id) {
        return ResponseEntity.ok(companyService.findById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<SearchedCompany>> search(@RequestParam("q") String text) {
        return ResponseEntity.ok(companyService.searchByName(text));
    }


}
