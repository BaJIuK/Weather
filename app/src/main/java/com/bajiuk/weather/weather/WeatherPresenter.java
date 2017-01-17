package com.bajiuk.weather.weather;

import com.bajiuk.weather.api.WeatherApi;
import com.bajiuk.weather.api.WeatherApiWrapper;
import com.bajiuk.weather.mvp.Presenter;

public class WeatherPresenter implements Presenter<WeatherView> {
  private WeatherApiWrapper api;

  public WeatherPresenter(WeatherApiWrapper api) {
    this.api = api;
  }

  @Override public void takeView(WeatherView view) {

  }

  @Override public void dropView(WeatherView view) {

  }

  //@Override protected void onLoad(Bundle savedInstanceState) {
  //}
  //
  //@Override protected void onSave(Bundle outState) {
  //}
}
