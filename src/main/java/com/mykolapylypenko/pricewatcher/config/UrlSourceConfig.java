package com.mykolapylypenko.pricewatcher.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.GZIPInputStream;

public class UrlSourceConfig {

    public static String getURLSource(String sURL) throws IOException {
        URL url = new URL(sURL);
        URLConnection urlCon = url.openConnection();
        urlCon.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
        BufferedReader in = null;

        if (urlCon.getHeaderField("Content-Encoding") != null
                && urlCon.getHeaderField("Content-Encoding").equals("gzip")) {
            in = new BufferedReader(new InputStreamReader(new GZIPInputStream(
                    urlCon.getInputStream())));
        } else {
            in = new BufferedReader(new InputStreamReader(
                    urlCon.getInputStream()));
        }

        String inputLine;
        StringBuilder sb = new StringBuilder();

        while ((inputLine = in.readLine()) != null)
            sb.append(inputLine);
        in.close();

        return sb.toString();
    }

    private static String toString(InputStream inputStream) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"))) {
            String inputLine;
            StringBuilder stringBuilder = new StringBuilder();
            while ((inputLine = bufferedReader.readLine()) != null) {
                stringBuilder.append(inputLine);
            }

            return stringBuilder.toString();
        }
    }
}
