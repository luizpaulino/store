package com.store.items.dto.external;

import com.store.items.dto.external.request.CreateSession;
import com.store.items.dto.external.request.TokenValidate;
import com.store.items.dto.external.response.AccessToken;
import com.store.items.dto.external.response.TokenValid;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface AuthService {

    @POST("/v1/session/validate")
    Call<TokenValid> validateToken(@Header("Authorization") String authorization, @Body TokenValidate tokenRequest);
    @POST("/v1/session")
    Call<AccessToken> createSession(@Body CreateSession createSession);
}
