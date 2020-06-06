package com.reviewcongty.backend.core.dao.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.TextScore;

@Data
@Document(collection = "company")
public class SearchedCompany {
    @Id
    @TextIndexed
    private String id;
    private String imageName;
    @TextIndexed
    private String name;
    @TextScore
    private Float score;

}
