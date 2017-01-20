package com.bajiuk.weather.tabs;

import com.bajiuk.weather.base.MvpPresenter;
import com.bajiuk.weather.db.StorageApi;
import com.bajiuk.weather.weather.WeatherView;
import rx.Completable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class TabsPresenter implements MvpPresenter<TabsView<TabsViewModel>> {

  private StorageApi storageApi;
  private TabsView view;

  public TabsPresenter(StorageApi storageApi) {
    this.storageApi = storageApi;
  }

  @Override public void attach(TabsView view) {
    this.view = view;
  }

  @Override public void detach() {
    view = null;
  }

  public void loadData() {
    storageApi.getCities()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(cities -> {
          cities.add(0, WeatherView.CURRENT);
          TabsViewModel model = new TabsViewModel(cities);
          model.setSelected(WeatherView.CURRENT);
          view.setData(model);
        });
  }

  public void addCity(String city) {
    storageApi.addCity(city)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(() -> view.addTab(city));
  }
}
