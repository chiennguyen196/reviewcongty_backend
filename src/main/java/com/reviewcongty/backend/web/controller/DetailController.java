package com.reviewcongty.backend.web.controller;

import com.reviewcongty.backend.core.dao.entity.Company;
import com.reviewcongty.backend.core.dao.entity.Review;
import com.reviewcongty.backend.core.service.CompanyService;
import com.reviewcongty.backend.core.service.ReviewService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DetailController {

    private final CompanyService companyService;
    private final ReviewService reviewService;

    public DetailController(CompanyService companyService, ReviewService reviewService) {
        this.companyService = companyService;
        this.reviewService = reviewService;
    }

    @GetMapping("/companies/{id}")
    public String getCompany(
            @PathVariable String id,
            @RequestParam(value = "tab", defaultValue = "reviews") String tab,
            @RequestParam(value = "page", defaultValue = "1") int page,
            Model model) {
        int startPage = page - 1;
        int pageSize = 15;
        Company company = companyService.findById(id);
        Page<Review> reviewPage = reviewService.findByCompanyId(id, startPage, pageSize);

        model.addAttribute("tab", tab);
        model.addAttribute("company", company);
        model.addAttribute("reviewPage", reviewPage);

        return "company-detail";
    }
}
