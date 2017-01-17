package com.bajiuk.weather.tabs;

import com.bajiuk.weather.base.MvpView;
import java.util.List;

public interface TabsView extends MvpView {
  void setData(List<String> tabs);
}
