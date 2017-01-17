package com.bajiuk.weather.weather;

import com.bajiuk.weather.api.WeatherApiWrapper;
import com.bajiuk.weather.base.MvpPresenter;
import com.bajiuk.weather.db.Notificator;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class WeatherPresenter implements MvpPresenter<WeatherView> {

  private Notificator notificator;
  private WeatherApiWrapper api;
  private WeatherView view;
  private CompositeSubscription subscriptions = new CompositeSubscription();

  public WeatherPresenter(Notificator notificator, WeatherApiWrapper api) {
    this.notificator = notificator;
    this.api = api;
  }

  @Override public void attach(WeatherView view) {
    this.view = view;
    loadWeather(Notificator.CURRENT);
    initNotifier();
  }

  @Override public void detach() {
    subscriptions.clear();
    view = null;
  }

  private void loadWeather(String name) {
    if (Notificator.CURRENT.equals(name)) {
      subscriptions.add(api.getByLatLng(0, 0)
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(response -> {
            WeatherData data = new WeatherData(response.getName(), response.getMain().getTemp());
            if (view != null) {
              view.setData(data);
            }
          }, Throwable::printStackTrace));
    } else {
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

  private void initNotifier() {
    subscriptions.add(notificator.getVisibleNotifier()
        .subscribeOn(AndroidSchedulers.mainThread())
        .subscribe(this::loadWeather));
  }
}
