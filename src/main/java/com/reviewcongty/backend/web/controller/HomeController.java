package com.reviewcongty.backend.web.controller;

import com.reviewcongty.backend.dao.entity.Company;
import com.reviewcongty.backend.dao.entity.Review;
import com.reviewcongty.backend.service.CompanyService;
import com.reviewcongty.backend.service.ReviewService;
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
            @RequestParam(value = "page", defaultValue = "1") int page,
            Model model) {
        Page<Company> companyPage = companyService.getLatestUpdated(page - 1, 20);
        List<Review> recentlyReviews = reviewService.getRecentlyReviews(15);

        model.addAttribute("companyPage", companyPage);
        model.addAttribute("recentlyReviews", recentlyReviews);

        return "index";
    }
}
