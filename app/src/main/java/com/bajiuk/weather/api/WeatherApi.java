package com.bajiuk.weather.api;

import com.bajiuk.weather.api.model.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface WeatherApi {

  @GET("weather")
  Observable<Response> getByLatLng(@Query("lat") double longitude, @Query("lon") double latitude, @Query("APPID") String appId);

  @GET("weather")
  Observable<Response> getByName(@Query("q") String cityName, @Query("APPID") String appId);
}
