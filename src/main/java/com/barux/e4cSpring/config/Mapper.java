package com.barux.e4cSpring.config;

public interface Mapper <A,B>{
    B mapTo(A a);
    A mapFrom(B b);
}
