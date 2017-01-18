package com.bajiuk.weather.tabs;

import com.bajiuk.weather.base.MvpPresenter;
import com.bajiuk.weather.db.StorageApi;

public class TabsPresenter implements MvpPresenter<TabsView> {

  private StorageApi storageApi;
  private TabsView view;

  public TabsPresenter(StorageApi storageApi) {
    this.storageApi = storageApi;
  }

  @Override public void attach(TabsView view) {
    this.view = view;
    view.setTabs(storageApi.getCities());
  }

  @Override public void detach() {
    view = null;
  }

  public void addCity(String city) {
    storageApi.addCity(city);
    if (view != null) {
      view.addTab(city);
    }
  }
}
