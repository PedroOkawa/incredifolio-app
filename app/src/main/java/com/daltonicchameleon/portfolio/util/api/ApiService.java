package com.daltonicchameleon.portfolio.util.api;

import com.daltonicchameleon.portfolio.util.api.response.UserResponse;
import com.daltonicchameleon.portfolio.util.constants.Constants;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import rx.Observable;

/**
 * portfolio-app
 * Created in 3/24/17 by the following authors:
 * Pedro Okawa
 */
public interface ApiService {

    /** SESSION **/
    @GET("session/validate")
    Observable<Void> validate(@Header(Constants.API_SERVICE_FIELD_TOKEN) String token);

    /** USER **/
    @FormUrlEncoded
    @POST("user/register")
    Observable<UserResponse> registerUser(
            @Field(Constants.API_SERVICE_FIELD_USERNAME) String username,
            @Field(Constants.API_SERVICE_FIELD_PASSWORD) String password
    );

    @FormUrlEncoded
    @POST("user/authenticate")
    Observable<UserResponse> authenticateUser(
            @Field(Constants.API_SERVICE_FIELD_USERNAME) String username,
            @Field(Constants.API_SERVICE_FIELD_PASSWORD) String password
    );

}
