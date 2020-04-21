package com.reviewcongty.backend.service;

import com.reviewcongty.backend.dao.entity.Review;
import org.springframework.data.domain.Page;

public interface ReviewService {
    Page<Review> findByCompanyId(String companyId, int page, int pageSize);
}
