package com.b3i.learn.java.spring.tutorial.restapi.mappers;

public interface Mapper<A, B> {
    B mapTo(A a);
    A mapFrom(B b);
}
