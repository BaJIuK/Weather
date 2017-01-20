package com.bajiuk.weather.db;

import com.activeandroid.query.Select;
import com.bajiuk.weather.db.model.City;
import java.util.LinkedList;
import java.util.List;
import rx.Completable;
import rx.Observable;

public class StorageApiImpl implements StorageApi {

  @Override public Completable addCity(String name) {
    return Completable.fromAction(() -> {
      City newCity = new City();
      newCity.name = name;
      newCity.timestamp = System.currentTimeMillis();
      newCity.save();
    });
  }

  @Override public Observable<List<String>> getCities() {
    return Observable.fromCallable(() -> {
      List<City> cities = new Select().from(City.class).execute();
      List<String> result = new LinkedList<>();
      for (City city : cities) {
        result.add(city.name);
      }
      return result;
    });
  }
}
