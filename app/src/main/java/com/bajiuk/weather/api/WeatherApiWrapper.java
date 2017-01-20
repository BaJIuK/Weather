package com.bajiuk.weather.api;

import com.bajiuk.weather.api.model.Response;
import rx.Observable;

public class WeatherApiWrapper {
  private String appId;
  private WeatherApi weatherApi;

  public WeatherApiWrapper(String appId, WeatherApi weatherApi) {
    this.appId = appId;
    this.weatherApi = weatherApi;
  }

  public Observable<Response> getByLatLng(double latitude, double longitude) {
    return weatherApi.getByLatLng(latitude, longitude, appId);
  }


  public Observable<Response> getByName(String cityName) {
    return weatherApi.getByName(cityName, appId);
  }

}
