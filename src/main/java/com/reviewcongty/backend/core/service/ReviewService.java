package com.reviewcongty.backend.core.service;

import com.reviewcongty.backend.api.request.ReactRequest;
import com.reviewcongty.backend.api.request.ReplyRequest;
import com.reviewcongty.backend.api.request.ReviewRequest;
import com.reviewcongty.backend.core.dao.entity.Review;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ReviewService {
    Page<Review> findByCompanyId(String companyId, int page, int pageSize);

    List<Review> getRecentlyReviews(int limit);

    Review create(String companyId, ReviewRequest request);

    Review reply(String reviewId, ReplyRequest request);

    Review react(String reviewId, ReactRequest request);
}
