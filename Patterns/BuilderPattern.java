package Patterns;

import java.util.HashMap;
import java.util.Map;

/**
 * Use when there are many optional fields of an entity or class
 */
public class BuilderPattern {
    public static void main(String[] args) {

        HttpRequest request = new HttpRequest.Builder().requestUri("https://google.com").method("POST").build();
        request.send();
    }
}


class HttpRequest {
    private String baseUrl;
    private String requestUri;
    private int port;
    private String method;
    private Map<String, String> header;


    public void send(){
        System.out.println("HTTP Request sent for URL: " + this.method + " "+  this.requestUri);
    }

    public static class Builder {
        private final HttpRequest request = new HttpRequest();

        public Builder baseUrl(String baseUrl){
            request.baseUrl=baseUrl;
            return this;
        }

        public Builder requestUri(String requestUri){
            request.requestUri=requestUri;
            return this;
        }

        public Builder port(int port){
            request.port=port;
            return this;
        }

        public Builder method(String method){
            request.method=method;
            return this;
        }

        public Builder headers(String key, String value){
            if (request.header == null) {
                request.header = new HashMap<>();
            }
            request.header.put(key, value);
            return this;
        }

        public HttpRequest build(){
            if(request.requestUri == null){
                throw new IllegalStateException("URL cannot be null");
            }
            if(request.method == null){
                throw new IllegalStateException("Method cannot be null");
            }

            return request;
        }

    }
}
