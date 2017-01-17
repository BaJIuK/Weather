package com.bajiuk.weather.api;

import com.bajiuk.weather.api.model.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface WeatherApi {

  @GET("weather")
  Observable<Response> getByLatLng(@Query("lat") double longitude, @Query("lon") double latitude);

  @GET("weather")
  Observable<Response> getByName(@Query("id") String cityName);
}
