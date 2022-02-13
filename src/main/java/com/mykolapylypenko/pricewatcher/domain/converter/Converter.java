package com.mykolapylypenko.pricewatcher.domain.converter;

public interface Converter<F, T> {
    T convert(F from);
}
