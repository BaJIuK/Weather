package com.bajiuk.weather.db;

import java.util.List;
import rx.Completable;
import rx.Observable;

public interface StorageApi {
  Completable addCity(String name);
  Observable<List<String>> getCities();
}
