package com.reviewcongty.backend.core.service.impl;

import com.reviewcongty.backend.api.request.ReviewRequest;
import com.reviewcongty.backend.core.dao.entity.Company;
import com.reviewcongty.backend.core.dao.entity.Review;
import com.reviewcongty.backend.core.dao.repo.CompanyRepository;
import com.reviewcongty.backend.core.dao.repo.ReviewRepository;
import com.reviewcongty.backend.core.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Slf4j
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final CompanyRepository companyRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyRepository companyRepository) {
        this.reviewRepository = reviewRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public Page<Review> findByCompanyId(String companyId, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(Sort.Order.desc("created")));
        return reviewRepository.findByCompanyId(companyId, pageable);
    }

    @Override
    public List<Review> getRecentlyReviews(int limit) {
        Slice<Review> reviewSlice = reviewRepository.findAll(PageRequest.of(0, limit, Sort.by(Sort.Order.desc("created"))));
        return reviewSlice.getContent();
    }

    @Override
    public Review create(String companyId, ReviewRequest request) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found company with id: " + companyId));

        Review review = new Review();

        review.setName(request.getName());
        review.setPosition(request.getPosition());
        review.setContent(request.getContent());
        review.setRating(request.getRating());

        review.setCompanyId(company.getId());
        review.setCompanyName(company.getName());

        return reviewRepository.save(review);
    }
}
