package com.reviewcongty.backend.core.service;

import com.reviewcongty.backend.core.dao.entity.Company;
import com.reviewcongty.backend.core.dao.entity.SearchedCompany;
import org.springframework.data.domain.Page;

import java.util.List;


public interface CompanyService {
    Page<Company> getLatestUpdated(int page, int pageSize);

    Page<Company> getBestCompanies(int page, int pageSize);

    Page<Company> getWorstCompanies(int page, int pageSize);

    Page<Company> getDramaCompanies(int page, int pageSize);

    Company findById(String id);

    List<SearchedCompany> searchByName(String name);
}
