package com.bajiuk.weather.tabs;

import com.bajiuk.weather.base.MvpView;
import com.bajiuk.weather.base.MvpViewModel;
import java.util.List;

public interface TabsView<M extends MvpViewModel> extends MvpView<M> {
  void addTab(String name);
  void showWeather(String name);
}
