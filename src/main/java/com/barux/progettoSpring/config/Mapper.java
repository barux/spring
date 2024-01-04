package com.barux.progettoSpring.config;

public interface Mapper <A,B>{
    B mapTo(A a);
    A mapFrom(B b);
}
