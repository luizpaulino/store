package com.store.payment.dto.external;

import com.store.payment.dto.external.response.CartValidResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Url;

public interface CartService {

    @GET
    Call<CartValidResponse> getCart(@Url String baseUrl, @Header("Authorization") String authorization);
}
