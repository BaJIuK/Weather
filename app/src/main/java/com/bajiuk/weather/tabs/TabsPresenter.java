package com.bajiuk.weather.tabs;

import com.bajiuk.weather.base.MvpPresenter;
import com.bajiuk.weather.db.StorageApi;

/**
 * Created by Valentin on 18.01.2017.
 */

public class TabsPresenter implements MvpPresenter<TabsView> {

  private StorageApi storageApi;

  public TabsPresenter(StorageApi storageApi) {
    this.storageApi = storageApi;
  }

  @Override public void attach(TabsView view) {
    view.setData(storageApi.getCities());
  }

  @Override public void detach() {
  }

}
