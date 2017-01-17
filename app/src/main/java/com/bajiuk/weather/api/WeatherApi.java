package com.bajiuk.weather.api;

import com.bajiuk.weather.api.model.Response;
import rx.Observable;

public interface WeatherApi {
  Observable<Response> getByLatLng(double longitude, double latitude);

  Observable<Response> getByName(String cityName);
}
