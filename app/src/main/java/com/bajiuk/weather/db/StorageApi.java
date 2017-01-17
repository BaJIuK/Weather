package com.bajiuk.weather.db;

/**
 * Created by Valentin on 17.01.2017.
 */

public interface StorageApi {
  void addCity(String name);
  void addLocation(double lon, double lat);
}
