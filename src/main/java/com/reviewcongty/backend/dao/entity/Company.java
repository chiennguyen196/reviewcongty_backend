package com.reviewcongty.backend.dao.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Company {
    @Id
    private String id;
    private String imageName;
    private String name;
    private Float rating;
    private Long ratingCount;
    private String companyType;
    private String size;
    private Date lastUpdated;
}
