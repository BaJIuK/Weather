package com.bajiuk.weather.tabs;

import com.bajiuk.weather.base.MvpPresenter;
import com.bajiuk.weather.db.Notificator;
import com.bajiuk.weather.db.StorageApi;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Valentin on 18.01.2017.
 */

public class TabsPresenter implements MvpPresenter<TabsView> {

  private StorageApi storageApi;
  private Notificator notificator;
  private Subscription subscription;
  private TabsView view;

  public TabsPresenter(StorageApi storageApi, Notificator notificator) {
    this.storageApi = storageApi;
    this.notificator = notificator;
  }

  private void setupNotificationListener() {
    subscription = notificator.getAddNotifier()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(newCity -> refreshCities());
  }

  @Override public void attach(TabsView view) {
    this.view = view;
    setupNotificationListener();
  }

  @Override public void detach() {
    if (subscription != null) {
      subscription.unsubscribe();
    }
    view = null;
  }

  public void onTabSelected(String cityName) {
    notificator.setVisible(cityName);
  }

  private void refreshCities() {
    if (view != null) {
      view.setData(storageApi.getCities());
    }
  }

}
