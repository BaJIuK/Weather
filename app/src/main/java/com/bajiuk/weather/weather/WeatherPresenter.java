package com.bajiuk.weather.weather;

import com.bajiuk.weather.api.WeatherApiWrapper;
import com.bajiuk.weather.base.MvpPresenter;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class WeatherPresenter implements MvpPresenter<WeatherView> {

  private WeatherApiWrapper api;
  private WeatherView view;
  private CompositeSubscription subscriptions = new CompositeSubscription();

  public WeatherPresenter(WeatherApiWrapper api) {
    this.api = api;
  }

  @Override public void attach(WeatherView view) {
    this.view = view;
    loadWeather("london");
  }

  @Override public void detach() {
    subscriptions.clear();
    view = null;
  }

  public void load(String cityName) {
    if (cityName == null) {
      return;
    } else {
      loadWeather(cityName);
    }
  }

  private void loadWeather(String name) {
    subscriptions.add(api.getByName(name)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(response -> {
          WeatherData data = new WeatherData(response.getName(), response.getMain().getTemp());
          if (view != null) {
            view.setData(data);
          }
        }, Throwable::printStackTrace));
  }
}
