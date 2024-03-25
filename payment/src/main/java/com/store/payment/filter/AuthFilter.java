package com.store.payment.filter;

import com.store.payment.dto.errors.PaymentException;
import com.store.payment.dto.external.AuthService;
import com.store.payment.dto.external.request.CreateSession;
import com.store.payment.dto.external.request.TokenValidate;
import com.store.payment.dto.external.response.AccessToken;
import com.store.payment.dto.external.response.TokenValid;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;

@Component
public class AuthFilter extends OncePerRequestFilter {

    @Value("${authentication-service.user.username}")
    private String username;

    @Value("${authentication-service.user.password}")
    private String password;

    private final Retrofit retrofit;

    public AuthFilter(Retrofit retrofit) {
        this.retrofit = retrofit;
    }
    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain
    )
            throws ServletException, IOException {

        AuthService authService = retrofit.create(AuthService.class);

        Response<AccessToken> accessTokenResponse =
                authService.createSession(new CreateSession(username, password)).execute();

        if (!accessTokenResponse.isSuccessful() || accessTokenResponse.body() == null) {
            throw new PaymentException("Error on creation token ");
        }

        String tokenToValidate = request.getHeader("Authorization").split(" ")[1];

        Response<TokenValid> tokenValidResponse =
                authService.validateToken(
                        "Bearer " + accessTokenResponse.body().getAccessToken(),
                        new TokenValidate(tokenToValidate)
                ).execute();

        if (
                !tokenValidResponse.isSuccessful()
                || tokenValidResponse.body() == null
                || !tokenValidResponse.body().getValid()
        ) {
            throw new PaymentException("Error on validation token");
        }

        filterChain.doFilter(request, response);
    }
}
