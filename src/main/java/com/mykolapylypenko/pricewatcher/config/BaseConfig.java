package com.mykolapylypenko.pricewatcher.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.regex.Pattern;

@Configuration
public class BaseConfig {
    private static final String EK_UA_PATTERN_LOW = "(?<=(<span  itemprop=\"lowPrice\" content=\"))(.*?)(?=\")";
    private static final String EK_UA_PATTERN_HIGH = "(?<=(<span itemprop=\"highPrice\" content=\"))(.*?)(?=\")";
    private static final String AMAZON_PATTERN = "(?<=(<span class=\"a-offscreen\">\\$))(.*?)(?=<\\/span>)";

    @Bean
    public Pattern ekUaLowMatcher() {
        return Pattern.compile(EK_UA_PATTERN_LOW);
    }

    @Bean
    public Pattern ekUaHighMatcher() {
        return Pattern.compile(EK_UA_PATTERN_HIGH);
    }

    @Bean
    public Pattern amazonMatcher() {
        return Pattern.compile(AMAZON_PATTERN);
    }
}
