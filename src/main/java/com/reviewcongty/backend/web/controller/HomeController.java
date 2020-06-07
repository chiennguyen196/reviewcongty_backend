package com.reviewcongty.backend.web.controller;

import com.reviewcongty.backend.core.dao.entity.Company;
import com.reviewcongty.backend.core.dao.entity.Review;
import com.reviewcongty.backend.core.service.CompanyService;
import com.reviewcongty.backend.core.service.ReviewService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    private final CompanyService companyService;
    private final ReviewService reviewService;

    public HomeController(CompanyService companyService, ReviewService reviewService) {
        this.companyService = companyService;
        this.reviewService = reviewService;
    }

    @GetMapping
    public String index(
            @RequestParam(value = "tab", defaultValue = "latest") String tab,
            @RequestParam(value = "page", defaultValue = "1") int page,
            Model model) {
        Page<Company> companyPage = null;
        int startPage = page - 1;
        int pageSize = 20;
        switch (tab) {
            case "latest":
                companyPage = companyService.getLatestUpdated(startPage, pageSize);
                break;
            case "drama":
                companyPage = companyService.getDramaCompanies(startPage, pageSize);
                break;
            case "best":
                companyPage = companyService.getBestCompanies(startPage, pageSize);
                break;
            case "worst":
                companyPage = companyService.getWorstCompanies(startPage, pageSize);
                break;
            default:
                return "not-found";
        }


        List<Review> recentlyReviews = reviewService.getRecentlyReviews(15);

        model.addAttribute("tab", tab);
        model.addAttribute("companyPage", companyPage);
        model.addAttribute("recentlyReviews", recentlyReviews);

        return "index";
    }
}
