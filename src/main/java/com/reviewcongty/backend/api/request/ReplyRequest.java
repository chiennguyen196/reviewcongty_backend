package com.reviewcongty.backend.api.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class ReplyRequest {
    @Size(min = 2, max = 20, message = "Tên có độ dài từ {min} đến {max} ký tự")
    private String name;
    @NotEmpty
    private String content;
    private String reaction;
}
