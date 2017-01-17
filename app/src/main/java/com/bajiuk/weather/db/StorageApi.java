package com.bajiuk.weather.db;

import java.util.List;

public interface StorageApi {
  void addCity(String name);

  List<String> getCities();
}
