package com.reviewcongty.backend.service;

import com.reviewcongty.backend.dao.entity.Company;
import org.springframework.data.domain.Page;


public interface CompanyService {
    Page<Company> getLatestUpdated(int page, int pageSize);

    Page<Company> getBestCompanies(int page, int pageSize);

    Page<Company> getWorstCompanies(int page, int pageSize);

    Page<Company> getDramaCompanies(int page, int pageSize);

    Company findById(String id);
}
