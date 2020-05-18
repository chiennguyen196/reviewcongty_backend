package com.reviewcongty.backend.service;

import com.reviewcongty.backend.api.request.ReviewRequest;
import com.reviewcongty.backend.dao.entity.Review;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ReviewService {
    Page<Review> findByCompanyId(String companyId, int page, int pageSize);
    List<Review> getRecentlyReviews(int limit);

    Review create(String companyId, ReviewRequest request);
}
