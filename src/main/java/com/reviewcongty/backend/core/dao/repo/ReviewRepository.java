package com.reviewcongty.backend.core.dao.repo;

import com.reviewcongty.backend.core.dao.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends MongoRepository<Review, String> {
    Page<Review> findByCompanyId(String companyId, Pageable pageable);
}
