package com.reviewcongty.backend.core.dao.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Reply {
    private String name;
    private String content;
    private Date created;
}
