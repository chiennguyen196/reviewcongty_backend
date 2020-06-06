package com.reviewcongty.backend.core.service.impl;

import com.reviewcongty.backend.core.dao.entity.Company;
import com.reviewcongty.backend.core.dao.entity.SearchedCompany;
import com.reviewcongty.backend.core.dao.repo.CompanyRepository;
import com.reviewcongty.backend.core.service.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository repository;
    private final MongoOperations operations;

    public CompanyServiceImpl(CompanyRepository repository, MongoOperations operations) {
        this.repository = repository;
        this.operations = operations;
    }


    @Override
    public Page<Company> getLatestUpdated(int page, int pageSize) {
        Pageable pageableRequest = PageRequest.of(page, pageSize, Sort.by(Sort.Order.desc("last_updated")));
        return repository.findAll(pageableRequest);
    }

    @Override
    public Page<Company> getBestCompanies(int page, int pageSize) {
        Pageable pageableRequest = PageRequest.of(page, pageSize, Sort.by(Sort.Order.desc("rating"), Sort.Order.desc("rating_count")));
        return repository.findAll(pageableRequest);
    }

    @Override
    public Page<Company> getWorstCompanies(int page, int pageSize) {
        Pageable pageableRequest = PageRequest.of(page, pageSize, Sort.by(Sort.Order.asc("rating"), Sort.Order.desc("rating_count")));
        return repository.findAll(pageableRequest);
    }

    @Override
    public Page<Company> getDramaCompanies(int page, int pageSize) {
        Pageable pageableRequest = PageRequest.of(page, pageSize, Sort.by(Sort.Order.desc("rating_count"), Sort.Order.asc("rating")));
        return repository.findAll(pageableRequest);
    }

    @Override
    public Company findById(String id) {
        Optional<Company> optional = repository.findById(id);
        return optional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found company with id: " + id));
    }

    @Override
    public List<SearchedCompany> searchByName(String name) {
        TextCriteria criteria = TextCriteria.forDefaultLanguage()
                .matching(name)
                .diacriticSensitive(true);
        TextQuery query = new TextQuery(criteria);
        query.setScoreFieldName("score");
        query.sortByScore();
        return operations.find(query, SearchedCompany.class);
    }
}
