package com.reviewcongty.backend.service.impl;

import com.reviewcongty.backend.dao.entity.Company;
import com.reviewcongty.backend.dao.repo.CompanyRepository;
import com.reviewcongty.backend.service.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository repository;

    public CompanyServiceImpl(CompanyRepository repository) {
        this.repository = repository;
    }


    @Override
    public Page<Company> getLatestUpdated(int page, int pageSize) {
        Pageable pageableRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Order.desc("last_updated")));
        return repository.findAll(pageableRequest);
    }
}
