package com.reviewcongty.backend.api.controller;

import com.reviewcongty.backend.dao.entity.Review;
import com.reviewcongty.backend.service.ReviewService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews/{company-id}")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping()
    public ResponseEntity<Page<Review>> getReviews(
            @PathVariable("company-id") String companyId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int pageSize) {
        return ResponseEntity.ok(reviewService.findByCompanyId(companyId, page, pageSize));
    }
}
