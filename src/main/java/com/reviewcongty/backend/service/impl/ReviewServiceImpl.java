package com.reviewcongty.backend.service.impl;

import com.reviewcongty.backend.dao.entity.Review;
import com.reviewcongty.backend.dao.repo.ReviewRepository;
import com.reviewcongty.backend.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository repository;

    public ReviewServiceImpl(ReviewRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<Review> findByCompanyId(String companyId, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(Sort.Order.desc("created")));
        return repository.findByCompanyId(companyId, pageable);
    }

    @Override
    public List<Review> getRecentlyReviews(int limit) {
        Slice<Review> reviewSlice = repository.findAll(PageRequest.of(0, limit, Sort.by(Sort.Order.desc("created"))));
        return reviewSlice.getContent();
    }
}
