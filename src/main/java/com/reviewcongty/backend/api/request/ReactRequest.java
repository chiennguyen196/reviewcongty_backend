package com.reviewcongty.backend.api.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ReactRequest {
    @NotEmpty
    private String reaction;
}
