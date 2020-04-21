package com.reviewcongty.backend.service;

import com.reviewcongty.backend.dao.entity.Company;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CompanyService {
    Page<Company> getLatestUpdated(int page, int pageSize);
}
