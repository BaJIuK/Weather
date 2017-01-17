package com.bajiuk.weather.weather;

import com.bajiuk.weather.api.WeatherApiWrapper;
import com.bajiuk.weather.mvp.Presenter;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class WeatherPresenter implements Presenter<WeatherView> {
  private WeatherApiWrapper api;
  private WeatherView view;

  public WeatherPresenter(WeatherApiWrapper api) {
    this.api = api;
  }

  @Override public void takeView(WeatherView view) {
    this.view = view;
    api.getByName("London")
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(response -> {
          view.showLocation("London");
          view.showTemperature(response.getMain().getTemp() + " ");
        }, Throwable::printStackTrace);
  }

  @Override public void dropView(WeatherView view) {
    this.view = null;
  }

  //@Override protected void onLoad(Bundle savedInstanceState) {
  //}
  //
  //@Override protected void onSave(Bundle outState) {
  //}
}
