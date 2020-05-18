package com.reviewcongty.backend.api.request;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class ReviewRequest {
    @Size(min = 2, max = 20, message = "Tên có độ dài từ {min} đến {max} ký tự")
    private String name;
    @Size(min = 2, max = 20, message = "Chức vụ có độ dài từ {min} đến {max} ký tự")
    private String position;
    @NotEmpty
    @Size(min = 10, message = "Nội dung có độ dài tối thiểu là {min} ký tự")
    private String content;
    @NotNull
    @Min(0)
    @Max(5)
    private Integer rating;
}
