package com.reviewcongty.backend.web.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public interface Pagination {
    int OFF_SET = 4;

    static List<Integer> getPageNumber(int page, int totalPage) {
        int leftPoint = Math.max(1, page - OFF_SET);
        int rightPoint = Math.min(totalPage, page + OFF_SET);
        int startPage = Math.max(1, leftPoint - (OFF_SET - (rightPoint - page)));
        int endPage = Math.min(totalPage, rightPoint + (OFF_SET - (page - leftPoint)));
        return IntStream.rangeClosed(startPage, endPage).boxed().collect(Collectors.toList());
    }
}
