package com.bajiuk.weather.tabs;

import com.bajiuk.weather.base.MvpView;
import java.util.List;

public interface TabsView extends MvpView {
  void setTabs(List<String> tabs);
  void addTab(String name);
  void showWeather(String name);
}
