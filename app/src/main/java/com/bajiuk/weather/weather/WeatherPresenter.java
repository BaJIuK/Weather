package com.bajiuk.weather.weather;

import com.bajiuk.weather.api.WeatherApiWrapper;
import com.bajiuk.weather.base.MvpPresenter;
import com.bajiuk.weather.location.LocationProvider;
import com.bajiuk.weather.location.PermissionException;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class WeatherPresenter implements MvpPresenter<WeatherView> {

  private WeatherApiWrapper api;
  private LocationProvider locationProvider;
  private WeatherView view;
  private Subscription subscription;

  public WeatherPresenter(WeatherApiWrapper api, LocationProvider locationProvider) {
    this.api = api;
    this.locationProvider = locationProvider;
  }

  @Override public void attach(WeatherView view) {
    this.view = view;
  }

  @Override public void detach() {
    if (subscription != null) {
      subscription.unsubscribe();
    }
    view = null;
  }

  public void load(String cityName) {
    if (!WeatherView.CURRENT.equals(cityName)) {
      loadForCity(cityName);
    } else {
      loadForCurrentLocation();
    }
  }

  private void loadForCity(String name) {
    WeatherViewModel data = new WeatherViewModel(null, 0, WeatherViewModel.State.LOADING);
    view.setData(data);
    subscription = api.getByName(name)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(response -> {
          data.setLocation(response.getName());
          data.setTemperature(response.getMain().getTemp());
          data.setState(WeatherViewModel.State.LOADED);
          view.setData(data);
        }, throwable -> {
          data.setState(WeatherViewModel.State.ERROR);
          data.setErrorType(WeatherViewModel.ErrorType.NETWORK);
          view.setData(data);
        });
  }

  private void loadForCurrentLocation() {
    WeatherViewModel data = new WeatherViewModel(null, 0, WeatherViewModel.State.LOADING);
    view.setData(data);
    subscription = locationProvider.getCurrentLocation()
        .flatMap(location -> Observable.just(true)
            .subscribeOn(Schedulers.io())
            .flatMap(ok -> api.getByLatLng(location.getLatitude(), location.getLongitude())))
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(response -> {
          data.setLocation(response.getName());
          data.setTemperature(response.getMain().getTemp());
          data.setState(WeatherViewModel.State.LOADED);
          view.setData(data);
        }, throwable -> {
          data.setState(WeatherViewModel.State.ERROR);
          data.setErrorType(
              throwable instanceof PermissionException ? WeatherViewModel.ErrorType.LOCATION
                  : WeatherViewModel.ErrorType.NETWORK);
          view.setData(data);
        });
  }
}
