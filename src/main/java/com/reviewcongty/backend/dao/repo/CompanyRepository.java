package com.reviewcongty.backend.dao.repo;

import com.reviewcongty.backend.dao.entity.Company;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends MongoRepository<Company, String> {
}
