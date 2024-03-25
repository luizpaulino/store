package com.store.payment.dto.external;

import com.store.payment.dto.external.request.CreateSession;
import com.store.payment.dto.external.request.TokenValidate;
import com.store.payment.dto.external.response.AccessToken;
import com.store.payment.dto.external.response.TokenValid;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.Set;

public interface AuthService {

    @POST("/v1/session/validate")
    Call<TokenValid> validateToken(@Header("Authorization") String authorization, @Body TokenValidate tokenRequest);
    @POST("/v1/session")
    Call<AccessToken> createSession(@Body CreateSession createSession);
    @GET("/v1/users/{userId}/roles")
    Call<Set<String>> rolesByUser(@Path("userId") String userID, @Header("Authorization") String authorization);
}
