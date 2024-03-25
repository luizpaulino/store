package com.store.cart.dto.external;

import com.store.cart.dto.external.response.ItemValid;
import retrofit2.Call;
import retrofit2.http.*;

public interface ItemsService {

    @GET
    Call<ItemValid> getItem(@Url String baseUrl, @Header("Authorization") String authorization);
}
