package com.bajiuk.weather.tabs;

import com.bajiuk.weather.base.MvpViewModel;
import java.util.List;

public class TabsViewModel implements MvpViewModel {

  private List<String> tabs;
  private String selected;

  public TabsViewModel(List<String> tabs) {
    this.tabs = tabs;
  }

  public List<String> getTabs() {
    return tabs;
  }

  public void setTabs(List<String> tabs) {
    this.tabs = tabs;
  }

  public String getSelected() {
    return selected;
  }

  public void setSelected(String selected) {
    this.selected = selected;
  }

  @Override public boolean isEmpty() {
    return tabs == null;
  }
}
