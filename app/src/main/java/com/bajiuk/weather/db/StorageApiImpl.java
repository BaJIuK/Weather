package com.bajiuk.weather.db;

import com.activeandroid.query.Select;
import com.bajiuk.weather.db.model.City;
import java.util.LinkedList;
import java.util.List;

public class StorageApiImpl implements StorageApi {

  @Override public void addCity(String name) {
    City newCity = new City();
    newCity.name = name;
    newCity.timestamp = System.currentTimeMillis();
    newCity.save();
  }

  @Override public List<String> getCities() {
    List<City> cities = new Select().from(City.class).execute();
    List<String> result = new LinkedList<>();
    for (City city : cities) {
      result.add(city.name);
    }
    return result;
  }
}
