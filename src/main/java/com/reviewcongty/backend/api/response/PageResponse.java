package com.reviewcongty.backend.api.response;

import lombok.Data;

import java.util.List;

@Data
public class PageResponse<T> {
    private List<T> items;
    private int number;
    private int size;
    private int totalPage;
    private long totalElements;
    private boolean last;
    private boolean first;
    private int numberOfElements;
    private boolean empty;

}
