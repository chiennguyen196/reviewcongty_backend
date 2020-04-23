package com.reviewcongty.backend.transform;

import com.reviewcongty.backend.dto.response.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class PageTransformer<T> implements Transformer<PageResponse<T>, Page<T>> {
    public PageResponse<T> transform(Page<T> page) {
        PageResponse<T> response = new PageResponse<>();

        response.setItems(page.getContent());
        response.setSize(page.getSize());
        response.setEmpty(page.isEmpty());
        response.setFirst(page.isFirst());
        response.setLast(page.isLast());
        response.setNumber(page.getNumber() + 1);
        response.setNumberOfElements(page.getNumberOfElements());
        response.setTotalElements(page.getTotalElements());
        response.setTotalPage(page.getTotalPages());
        return response;
    }
}
