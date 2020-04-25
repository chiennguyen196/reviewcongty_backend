package com.reviewcongty.backend.service;

import com.reviewcongty.backend.dao.entity.Company;
import com.reviewcongty.backend.dao.entity.SearchedCompany;
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
