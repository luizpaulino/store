package com.store.items.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Configuration
public class AuthClient {

    @Value("${authentication-service.server.host}")
    private String authServiceHost;

    @Value("${authentication-service.server.port}")
    private String authServicePort;

    @Bean
    public Retrofit retrofit() {
        return new Retrofit.Builder()
                .baseUrl("http://" + authServiceHost + ":" + authServicePort)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
