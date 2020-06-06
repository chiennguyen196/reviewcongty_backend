package com.reviewcongty.backend.core.dao.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Document
public class Review {
    @Id
    private String id;
    private String name;
    private String position;
    private Integer rating;
    @CreatedDate
    private Date created;
    private String content;
    private Integer numLikes = 0;
    private Integer numDislikes = 0;
    private Integer numDeleteRequests = 0;
    private String companyId;
    private String companyName;
    private List<Reply> replies = new ArrayList<>();
}
