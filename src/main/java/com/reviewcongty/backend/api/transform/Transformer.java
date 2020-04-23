package com.reviewcongty.backend.api.transform;

public interface Transformer<D, S> {
    D transform(S s);
}
