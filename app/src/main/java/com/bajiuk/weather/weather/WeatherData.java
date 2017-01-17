package com.bajiuk.weather.weather;

public class WeatherData {
  private String location;
  private double temperature;

  public WeatherData(String location, double temperature) {
    this.location = location;
    this.temperature = temperature;
  }

  public String getLocation() {
    return location;
  }

  public double getTemperature() {
    return temperature;
  }
}
