package com.bajiuk.weather.fab;

import com.bajiuk.weather.base.MvpPresenter;
import com.bajiuk.weather.db.StorageApi;

/**
 * Created by Valentin on 18.01.2017.
 */

public class FabPresenter implements MvpPresenter<FabView> {

  private StorageApi storageApi;

  public FabPresenter(StorageApi storageApi) {
    this.storageApi = storageApi;
  }

  public void addCity(String city) {
    storageApi.addCity(city);
  }

  @Override public void attach(FabView view) {

  }

  @Override public void detach() {

  }
}
