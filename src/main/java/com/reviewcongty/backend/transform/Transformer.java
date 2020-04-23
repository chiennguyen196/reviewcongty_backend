package com.reviewcongty.backend.transform;

public interface Transformer<D, S> {
    D transform(S s);
}
