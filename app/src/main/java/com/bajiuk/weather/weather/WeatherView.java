package com.bajiuk.weather.weather;
import com.bajiuk.weather.base.MvpView;

public interface WeatherView extends MvpView {
  void setData(WeatherData data);
}
