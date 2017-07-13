package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.RequestEnhancer;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeAccessTokenProvider;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import javax.annotation.PostConstruct;

@Component
public class AzureRequestEnhancerCustomizer {

    @Autowired
    private OAuth2RestTemplate userInfoRestTemplate;

    @PostConstruct
    public void testWiring() {
        AuthorizationCodeAccessTokenProvider authorizationCodeAccessTokenProvider = new AuthorizationCodeAccessTokenProvider();
        authorizationCodeAccessTokenProvider.setTokenRequestEnhancer(new AzureRequestEnhancer());
        userInfoRestTemplate.setAccessTokenProvider(authorizationCodeAccessTokenProvider);
    }
}

@Component
class AzureRequestEnhancer implements RequestEnhancer {

    public AzureRequestEnhancer() {
    }

    @Override
    public void enhance(AccessTokenRequest request, OAuth2ProtectedResourceDetails resource,
                        MultiValueMap<String, String> form, HttpHeaders headers) {
        form.set("resource", "https://graph.windows.net");
    }
}
