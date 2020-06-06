package com.reviewcongty.backend.core.dao.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document
public class Company {
    @Id
    private String id;
    private String imageName;
    private String name;
    private Float rating;
    private Long ratingCount;
    private String companyType;
    private String size;
    private String address;
    private Date lastUpdated;
}
