package main.logic.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

/**
 * @author F0urth
 */
public class WebReader {

    private static final Logger logger = LoggerFactory.getLogger(WebReader.class);

    private String url;
    private Optional<RequestWrapper> lastCallFromUrl;

    public WebReader(@NonNull String url) {
        this.url = url;
        this.lastCallFromUrl = Optional.empty();
    }

    public void setUrl(@NonNull String url) {
        this.url = url;
    }

    public String read() {
        return read(this.url);
    }

    private String read(String url) {
        if (this.url.equals(url)) {
            return this.lastCallFromUrl
                .filter(e -> e.isUrlTheSame(this.url))
                .orElseGet(() -> {
                    final String json = readFromWeb(url);
                    RequestWrapper rw = new RequestWrapper(url, json);
                    Optional<RequestWrapper> tmp = Optional.of(rw);
                    this.lastCallFromUrl = tmp;
                    return rw;
                }).responds;
        } else {
            return readFromWeb(url);
        }
    }

    private String readFromWeb(String url) {
        logger.info("Reading web");
        StringBuilder json = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
            new InputStreamReader(new URL(url).openStream()))){
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("Receive: " + json);
        return json.toString();
    }

    private static class RequestWrapper {
        private final String from;
        private final String responds;

        private RequestWrapper(@NonNull String from, @NonNull String responds) {
            this.from = from;
            this.responds = responds;
        }

        private boolean isUrlTheSame(String url) {
            return this.from.equals(url);
        }
    }
}
