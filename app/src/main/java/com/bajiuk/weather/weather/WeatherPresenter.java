package com.bajiuk.weather.weather;

import com.bajiuk.weather.api.WeatherApiWrapper;
import com.bajiuk.weather.base.MvpPresenter;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class WeatherPresenter implements MvpPresenter<WeatherView> {

  private WeatherApiWrapper api;
  private WeatherView view;
  private Subscription subscription;

  public WeatherPresenter(WeatherApiWrapper api) {
    this.api = api;
  }

  @Override public void attach(WeatherView view) {
    this.view = view;
    loadWeather();
  }

  @Override public void detach() {
    if (subscription != null) {
      subscription.unsubscribe();
    }
    view = null;
  }

  private void loadWeather() {
    subscription = api.getByName("London")
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(response -> {
          WeatherData data = new WeatherData(response.getName(), response.getMain().getTemp());
          if (view != null) {
            view.setData(data);
          }
        }, Throwable::printStackTrace);
  }
}
