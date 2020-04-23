package com.reviewcongty.backend.web.controller;

import com.reviewcongty.backend.dao.entity.Company;
import com.reviewcongty.backend.service.CompanyService;
import com.reviewcongty.backend.web.util.Pagination;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class HomeController {
    private final CompanyService companyService;

    public HomeController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public String index(
            @RequestParam(value = "page", defaultValue = "1") int page,
            Model model) {
        Page<Company> companyPage = companyService.getLatestUpdated(page - 1, 20);

        model.addAttribute("companyPage", companyPage);

        model.addAttribute("pageNumbers", Pagination.getPageNumber(companyPage.getNumber() + 1, companyPage.getTotalPages()));

        return "index";
    }
}
