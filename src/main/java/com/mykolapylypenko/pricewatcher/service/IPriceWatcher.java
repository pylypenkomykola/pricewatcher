package com.mykolapylypenko.pricewatcher.service;

import com.mykolapylypenko.pricewatcher.openapi.model.ProductDto;

public interface IPriceWatcher {
    String startWatching(ProductDto productDto);
}
