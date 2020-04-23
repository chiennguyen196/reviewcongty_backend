package com.reviewcongty.backend.dao.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Reply {
    private String name;
    private String content;
    private Date created;
}
