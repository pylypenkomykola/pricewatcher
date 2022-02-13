package com.mykolapylypenko.pricewatcher.domain.format;

import java.util.Arrays;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StringLogFormat {

    public static String format(String text, Object... objects) {
        return objectsCount(text, objects);
    }

    private static String objectsCount(String text, Object[] objects) {
        String regex = "\\{}";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        int groupCount = (int)matcher.results().count();

        for(int i = 0; i < groupCount; i++){
            if (objects.length > i) {
                text = text.replaceFirst(regex, objects[i].toString());
            } else {
                text = text.replaceFirst(regex, "");
            }
        }

        return text;
    }
}
