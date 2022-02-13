package com.mykolapylypenko.pricewatcher.controller;

import com.mykolapylypenko.pricewatcher.openapi.api.WatchApi;
import com.mykolapylypenko.pricewatcher.openapi.model.ProductDto;
import com.mykolapylypenko.pricewatcher.service.IPriceWatcher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PriceWatcherApiController implements WatchApi {

    private final IPriceWatcher priceWatcher;

    @Override
    public ResponseEntity<String> startWatching(ProductDto productDto) {
        String result = priceWatcher.startWatching(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}
