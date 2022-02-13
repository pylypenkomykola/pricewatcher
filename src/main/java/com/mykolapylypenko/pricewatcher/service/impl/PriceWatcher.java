package com.mykolapylypenko.pricewatcher.service.impl;

import com.mykolapylypenko.pricewatcher.config.UrlSourceConfig;
import com.mykolapylypenko.pricewatcher.openapi.model.ProductDto;
import com.mykolapylypenko.pricewatcher.service.IPriceWatcher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
@RequiredArgsConstructor
public class PriceWatcher implements IPriceWatcher {

    private final Pattern ekUaLowMatcher;
    private final Pattern ekUaHighMatcher;
    private final Pattern amazonMatcher;

    @Override
    public String startWatching(ProductDto productDto) {
        String source = "";

        try {
            source = UrlSourceConfig.getURLSource(productDto.getUrl());
            BufferedWriter bw = new BufferedWriter(new FileWriter("test.txt"));
            bw.write(source);
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String result = matchText(source, productDto);

        System.out.println(result);
        return null;
    }

    private String matchText(String source, ProductDto productDto) {
        return switch (productDto.getMarket()) {
            case AMAZON -> {
                Matcher amazonStringMatch = amazonMatcher.matcher(source);
                String result = "";
                if (amazonStringMatch.find()) {
                    result = amazonStringMatch.group(2);
                }
                yield result;
            }
            case EK_UA -> {
                Matcher ekLowMatcher = ekUaLowMatcher.matcher(source);
                Matcher ekHighMatcher = ekUaHighMatcher.matcher(source);

                BigDecimal lowPrice = null;
                BigDecimal highPrice = null;

                if (ekLowMatcher.find()) {
                    lowPrice = new BigDecimal(ekLowMatcher.group(2));
                }

                if (ekHighMatcher.find()) {
                    highPrice = new BigDecimal(ekHighMatcher.group(2));
                }

                yield String.valueOf(highPrice.add(lowPrice).divide(BigDecimal.valueOf(2), RoundingMode.FLOOR));
            }
        };
    }
}
