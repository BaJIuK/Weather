package com.bajiuk.weather.weather;

import com.bajiuk.weather.base.MvpViewModel;
import java.io.Serializable;

public class WeatherViewModel implements MvpViewModel {

  public enum State implements Serializable {
    LOADED, LOADING, ERROR, EMPTY
  }

  public enum ErrorType implements Serializable {
    NETWORK, LOCATION
  }

  private String location;
  private double temperature;
  private ErrorType errorType;
  private State state = State.EMPTY;

  public WeatherViewModel(String location, double temperature, State state) {
    this.state = state;
    this.location = location;
    this.temperature = temperature;
  }

  public void setErrorType(ErrorType errorType) {
    this.errorType = errorType;
  }

  public ErrorType getErrorType() {
    return errorType;
  }

  public State getState() {
    return state;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public void setTemperature(double temperature) {
    this.temperature = temperature;
  }

  public void setState(State state) {
    this.state = state;
  }

  public String getLocation() {
    return location;
  }

  @Override public boolean isEmpty() {
    return state == State.EMPTY;
  }

  public String getFormattedTemperature() {
    long temperature = Math.round(this.temperature - 273.15);
    return temperature + " \u2103";
  }
}
