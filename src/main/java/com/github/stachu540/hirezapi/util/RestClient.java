package com.github.stachu540.hirezapi.util;

import com.github.stachu540.hirezapi.api.Authentication;
import org.springframework.web.client.RestTemplate;

public class RestClient {

    private final RestTemplate rest = new RestTemplate();
    private final Authentication authentication;

    { rest.setErrorHandler(new RestErrorHandler()); }

    public RestClient(Authentication authentication) {this.authentication = authentication;}

    public <O extends Object> O request(String endpoint, Class<O> model, String... args) {
        String url = authentication.getUrl(endpoint, args);
        System.out.println(url);
        return rest.getForObject(url, model);
    }
}
